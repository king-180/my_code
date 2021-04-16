package hashtab;


import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {
        // 创建HashTab
        HashTab hashTab = new HashTab(7);
        // 写一个简单菜单
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出程序");

            key = sc.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = sc.nextInt();
                    System.out.println("输入名字");
                    String name = sc.next();
                    // 创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = sc.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

// 创建HashTab，管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;// 表示有多少条链表

    // 构造器
    public HashTab(int size) {
        this.size = size;
        // 初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // 这时候不能忘了分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员信息
    public void add(Emp emp) {
        // 根据员工id得到改员工应该添加到哪个链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp添加到对应链表
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    // 遍历所有的链表，遍历HashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    // 根据输入的id查找雇员
    public void findEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到雇员id=%d\n", (empLinkedListNo + 1), id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    // 编写一个散列函数，使用简单取模法
    public int hashFun(int id) {
        return id % size;
    }
}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

// 创建一个EmpLinkedList，表示链表
class EmpLinkedList {
    // 头指针指向第一个Emp
    private Emp head;

    // 添加雇员id，从后面加，假设从小到大
    public void add(Emp emp) {
        // 添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 添加后面的雇员
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    // 遍历链表的雇员信息
    public void list(int i) {
        if (head == null) {
            System.out.println("链表为空，没有雇员");
            return;
        }
        System.out.println("当前链表信息：");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }
}