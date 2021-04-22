package com.wx.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangxing
 * @date 2021/4/22 11:07
 */
public class ClassUtilsEx {
    private static final Map<String, Object> frameCache = new HashMap(16);
    private static final Map<String, List<Object[]>> methodParamsList = new ConcurrentHashMap(16);

    private ClassUtilsEx() {
    }

    /*public static Object invokeMethod(String className, String methodName, Map<String, Object> params) throws Exception {
        Object service = SpringContextLoader.getBean(className);
        Class cls = service.getClass();
        Object result = null;
        boolean hasMethod = false;
        Method[] var7 = cls.getDeclaredMethods();
        int var8 = var7.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Method method = var7[var9];
            if (method.getName().equals(methodName)) {
                hasMethod = true;
                if (method.getParameterTypes().length == 0) {
                    result = method.invoke(service);
                } else if (method.getParameterTypes().length > 0) {
                    List<Object[]> methodParamList = getMethodParams(method);
                    if (methodParamList.size() == 1) {
                        Object param = null;
                        String methodParamName = (String)((Object[])methodParamList.get(0))[0];
                        Class methodParamType = (Class)((Object[])methodParamList.get(0))[1];
                        if (!isSimpleClz(methodParamType) && !Collection.class.isAssignableFrom(methodParamType) && !params.containsKey(methodParamName)) {
                            param = BeanUtilsEx.convert(params, methodParamType);
                        } else {
                            param = BeanUtilsEx.convert(params.get(methodParamName), methodParamType);
                        }

                        result = method.invoke(service, param);
                    } else {
                        Object[] param = new Object[methodParamList.size()];

                        for(int i = 0; i < methodParamList.size(); ++i) {
                            String methodParamName = (String)((Object[])methodParamList.get(i))[0];
                            Class methodParamType = (Class)((Object[])methodParamList.get(i))[1];
                            if (!params.containsKey(methodParamName)) {
                                param[i] = null;
                            } else {
                                param[i] = BeanUtilsEx.convert(params.get(methodParamName), methodParamType);
                            }
                        }

                        result = method.invoke(service, param);
                    }
                }
                break;
            }
        }

        if (!hasMethod) {
            throw new ServiceException("类:" + className + "中没有找到方法:" + methodName);
        } else {
            return result;
        }
    }*/

    public static List<Object[]> getMethodParams(Method method) throws Exception {
        String clzName = filterCGLIB(method.getDeclaringClass().getName());
        String methodName = method.getName();
        if (!methodParamsList.containsKey(clzName + "." + methodName)) {
            initMethodParams(clzName, methodName, method);
        }

        return methodParamsList.get(clzName + "." + methodName);
    }

    private static synchronized void initMethodParams(String clzName, String methodName, Method method) throws Exception {
        if (!methodParamsList.containsKey(clzName + "." + methodName)) {
            ClassPool pool = new ClassPool(null);
            pool.insertClassPath(new ClassClassPath(method.getDeclaringClass()));
            CtMethod cm = pool.getMethod(clzName, methodName);
            CodeAttribute codeAttribute = cm.getMethodInfo().getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute("LocalVariableTable");
            List<Object[]> paramList = new ArrayList(cm.getParameterTypes().length);
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            Map<Integer, String> sortMap = new HashMap(16);

            int i;
            for (i = 0; i < attr.tableLength(); ++i) {
                if (attr.index(i) >= pos && attr.index(i) < cm.getParameterTypes().length + pos) {
                    sortMap.put(attr.index(i) - pos, attr.variableName(i));
                }
            }

            for (i = 0; i < cm.getParameterTypes().length; ++i) {
                Object[] param = new Object[]{sortMap.get(i), method.getParameterTypes()[i]};
                paramList.add(param);
            }

            methodParamsList.put(clzName + "." + methodName, paramList);
        }

    }

    private static String filterCGLIB(String className) {
        int cglibIdx = StringUtils.indexOf(className, "$$EnhancerBy");
        if (cglibIdx > -1) {
            className = StringUtils.substring(className, 0, cglibIdx);
        }

        return className;
    }

    public static Map<String, List<Method>> getClassMethodMap(Class clz) {
        Map<String, List<Method>> result = (Map) frameCache.get("ClassUtilsEx.getClassMethodMap" + clz.getName());
        if (result == null) {
            result = new HashMap(16);
            if (clz.getSuperclass() != null) {
                Map<String, List<Method>> superClassMethodMap = getClassMethodMap(clz.getSuperclass());

                for (String methodName : superClassMethodMap.keySet()) {
                    if (result.containsKey(methodName)) {
                        ((List) ((Map) result).get(methodName)).addAll(superClassMethodMap.get(methodName));
                    } else {
                        ((Map) result).put(methodName, superClassMethodMap.get(methodName));
                    }
                }

                result.putAll(getClassMethodMap(clz.getSuperclass()));
            }

            Method[] var7 = clz.getDeclaredMethods();

            for (Method method : var7) {
                if (!result.containsKey(method.getName())) {
                    List<Method> methodList = new ArrayList(10);
                    ((Map) result).put(method.getName(), methodList);
                }

                ((List) ((Map) result).get(method.getName())).add(method);
            }

            frameCache.put("ClassUtilsEx.getClassMethodMap" + clz.getName(), result);
        }

        return result;
    }

    public static List<Method> getClassMethodByAnnotation(Class clz, Class annoClz) throws Exception {
        List<Method> result = (List) frameCache.get("ClassUtilsEx.getClassMethodByAnnotation" + clz.getName() + annoClz.getName());
        if (result == null) {
            clz = Class.forName(filterCGLIB(clz.getName()), true, clz.getClassLoader());
            result = new ArrayList(10);
            Method[] var3 = clz.getMethods();
            int var4 = var3.length;

            for (Method method : var3) {
                if (method.getAnnotation(annoClz) != null) {
                    result.add(clz.getMethod(method.getName(), method.getParameterTypes()));
                }
            }

            frameCache.put("ClassUtilsEx.getClassMethodByAnnotation" + clz.getName() + annoClz.getName(), result);
        }

        return result;
    }

    public static Map<String, Class> getClassPropertyType(Class clz) {
        Map<String, Class> result = (Map) frameCache.get("ClassUtilsEx.getClassPropertyType" + clz.getName());
        if (result == null) {
            result = new HashMap(16);
            if (clz.getSuperclass() != null) {
                result.putAll(getClassPropertyType(clz.getSuperclass()));
            }

            Field[] var2 = clz.getDeclaredFields();
            int var3 = var2.length;

            for (Field field : var2) {
                ((Map) result).put(field.getName(), field.getType());
            }

            frameCache.put("ClassUtilsEx.getClassPropertyType" + clz.getName(), result);
        }

        return result;
    }

    public static boolean isSimpleClz(Class clz) {
        return clz.equals(String.class) || clz.equals(Long.class) || clz.equals(BigDecimal.class) || clz.equals(Integer.class) || clz.equals(Double.class) || clz.equals(Double.TYPE) || clz.equals(Integer.TYPE) || clz.equals(Long.TYPE) || clz.equals(Float.class) || clz.equals(Float.TYPE);
    }

    public static String tableName2ClassName(String tableName) {
        StringBuilder result = new StringBuilder();
        if (!StringUtils.isBlank(tableName)) {
            String[] tableNameArray = tableName.toLowerCase().split("_");

            for (String s : tableNameArray) {
                result.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
            }
        }

        return result.toString();
    }
}
