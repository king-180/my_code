package file;

import lombok.Data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/6/28 16:20
 */
public class ReadTxtDemo {

    static String res = "";
    static String res2 = "";

    public static void main(String[] args) throws IOException {

        String path1 = "D:\\downDD\\电站运营方\\charging_station_data.txt";
        String path2 = "D:\\downDD\\电站运营方\\t_company2.txt";
        String path3 = "D:\\downDD\\电站运营方\\t_company_type_20210628.csv";

        List<ChargingData> chargingDataTxt = new ArrayList<>(420000);
        List<Company> companyTxt = new ArrayList<>(180000);
        List<CompanyType> companyTypeTxt = new ArrayList<>(160000);

        readChargingDataToList(path1, chargingDataTxt);
        readCompanyToList(path2, companyTxt);
        readCompanyTypeToList(path3, companyTypeTxt);

        // 公司类型为 1 子公司 的所有 companyId
        List<String> companyIds = companyTypeTxt.stream()
                .filter(companyType -> "1".equals(companyType.getType()))
                .map(CompanyType::getCompanyId).collect(Collectors.toList());

        System.out.println("子公司的 companyId :" + companyIds);
        System.out.println("子公司的 companyId 个数 :" + companyIds.size());

        // 所有 公司类型为 1 子公司 的 orgCode
        List<String> orgCodes = companyTxt.stream().filter(company -> {
            String companyId = company.getCompanyId();
            return companyIds.contains(companyId);
        }).map(Company::getOrgCode).collect(Collectors.toList());

        System.out.println("子公司的 orgCode ：" + orgCodes);
        System.out.println("子公司的 orgCode 个数：" + orgCodes.size());

//        // flags 索引为 8 对应的数字是 2 的所有数据 6800条
//        List<ChargingData> chargingDataList = chargingDataTxt.stream().filter((chargingData -> {
//            String flags = chargingData.getFlags();
//            if (flags.length() > 8) {
//                char flag = flags.charAt(8);
//                return flag == '2';
//            }
//            return false;
//        })).filter(chargingData -> {
//            String assetOperator = chargingData.getAssetOperator();
//            if (orgCodes.contains(assetOperator)) {
//                return true;
//            }
//            System.out.println(chargingData.getStationId());
//            res += ", " + chargingData.getStationId();
//            return false;
//        }).collect(Collectors.toList());
//        res += "'";
//
//        System.out.println(chargingDataList.size());
//        System.out.println(res.replaceFirst(", ", ""));


        // 资产运营方为子公司 但是 FLAGS 第8位不是2
        AtomicInteger cnt = new AtomicInteger();
        AtomicInteger cnt2 = new AtomicInteger();
        List<ChargingData> chargingDataList1 = chargingDataTxt.stream().filter(chargingData -> {
            String assetOperator = chargingData.getAssetOperator();
            if (orgCodes.contains(assetOperator)) {
                cnt.getAndIncrement();
                return true;
            }
            return false;
        }).filter((chargingData -> {
            String flags = chargingData.getFlags();
            if (flags.length() > 8 && flags.charAt(8) == '2') {
                return true;
            }
            System.out.println(chargingData);
            res2 += "', '" + chargingData.getStationId();
            cnt2.getAndIncrement();
            return false;
        })).collect(Collectors.toList());
        res2 += "'";

//        System.out.println(chargingDataList1.size());
        System.out.println("chargingDataTxt 中子公司的个数：" + cnt.get());
        System.out.println("chargingDataTxt 中是子公司但是第8位不是2的个数：" + cnt2.get());
        System.out.println(res2.replaceFirst("', '", "'"));



    }

    private static void readChargingDataToList(String path, List<ChargingData> txt) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            ChargingData row = new ChargingData();
            while (line.contains(",,")) {
                line = line.replaceAll(",,", ",null,");
            }
            if (line.endsWith(",")) {
                line = line + "null";
            }
            if (line.startsWith(",")) {
                line = "null" + line;
            }
            String[] arr = line.split(",");
            if (arr.length == 5) {
                row.setStationId(arr[0]);
                row.setStationType(arr[1]);
                row.setFlags(arr[2]);
                row.setAssetOwner(arr[3]);
                row.setAssetOperator(arr[4]);
                txt.add(row);
            } else {
                System.out.println(Arrays.toString(arr));
            }
            count++;
        }
//        System.out.println(txt);
        System.out.println("读取总条数：" + count + " || 读取的txt的长度" + txt.size());
    }

    private static void readCompanyToList(String path, List<Company> txt) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            Company row = new Company();
            while (line.contains("#&##&#")) {
                line = line.replaceAll("#&##&#", "#&#null#&#");
            }
            if (line.endsWith("#&#")) {
                line = line + "null";
            }
            if (line.startsWith("#&#")) {
                line = "null" + line;
            }
            String[] arr = line.split("#&#");
            if (arr.length == 4) {
                row.setCompanyId(arr[0]);
                row.setCompanyName(arr[1]);
                row.setOrgCode(arr[2]);
                row.setCompanyType(arr[3]);
                txt.add(row);
            } else {
                System.out.println(Arrays.toString(arr));
            }
            count++;
        }
//        System.out.println(txt);
        System.out.println("读取总条数：" + count + " || 读取的txt的长度" + txt.size());
    }

    private static void readCompanyTypeToList(String path, List<CompanyType> txt) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            CompanyType row = new CompanyType();
            while (line.contains(",,")) {
                line = line.replaceAll(",,", ",null,");
            }
            if (line.endsWith(",")) {
                line = line + "null";
            }
            if (line.startsWith(",")) {
                line = "null" + line;
            }
            String[] arr = line.split(",");
            if (arr.length == 5) {
                row.setId(arr[0]);
                row.setCompanyId(arr[1]);
                row.setType(arr[2]);
                row.setTags(arr[3]);
                row.setCreateTime(arr[4]);
                txt.add(row);
            } else {
                System.out.println(Arrays.toString(arr));
            }
            count++;
        }
//        System.out.println(txt);
        System.out.println("读取总条数：" + count + " || 读取的txt的长度" + txt.size());
    }

    /*public static <T> List<T>  readTxt(String[] titles, String path, Class<T> clazz) throws IOException {
        List<T> txt = new ArrayList<>(100);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            T row;
            if (line.contains(",,")) {
                line = line.replaceAll(",,", ",null,");
            }
            if (line.endsWith(",")) {
                line = line + "null";
            }
            String[] arr = line.split(",");
            if (arr.length == 5) {
                row.setStationId(arr[0]);
                row.setStationType(arr[1]);
                row.setFlags(arr[2]);
                row.setAssetOwner(arr[3]);
                row.setAssetOperator(arr[4]);
                txt.add(row);
            } else {
                System.out.println(Arrays.toString(arr));
            }
            count++;
        }
//        System.out.println(txt);
        System.out.println("读取总条数：" + count + "||读取的txt的长度" + txt.size());

        return txt;
    }*/

}

@Data
class ChargingData {
    private String stationId;
    private String stationType;
    private String flags;
    private String assetOwner;
    private String assetOperator;
}

@Data
class Company {
    private String companyId;
    private String companyName;
    private String orgCode;
    private String companyType;
}

@Data
class CompanyType {
    private String id;
    private String companyId;
    private String type;
    private String tags;
    private String createTime;
}

