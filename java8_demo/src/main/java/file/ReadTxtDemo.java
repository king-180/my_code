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
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/6/28 16:20
 */
public class ReadTxtDemo {

    public static void main(String[] args) throws IOException {

        String path1 = "D:\\downDD\\电站运营方\\charging_station_data.txt";
        String path2 = "D:\\downDD\\电站运营方\\t_company2.txt";
        String path3 = "D:\\downDD\\电站运营方\\t_company_type_20210628.csv";

        List<ChargingData> txt1 = new ArrayList<>(420000);
        List<Company> txt2 = new ArrayList<>(180000);
        List<CompanyType> txt3 = new ArrayList<>(160000);

        readChargingDataToList(path1, txt1);
        readCompanyToList(path2, txt2);
        readCompanyTypeToList(path3, txt3);

        // 公司类型为 1 的所有companyId
        List<String> companyIds = txt3.stream()
                .filter(companyType -> "1".equals(companyType.getType()))
                .map(CompanyType::getCompanyId).collect(Collectors.toList());

        // 所有 公司类型为 1 的 orgCode
        List<String> orgCodes = txt2.stream().filter(company -> {
            String companyId = company.getCompanyId();
            return companyIds.contains(companyId);
        }).map(Company::getOrgCode).collect(Collectors.toList());

        // flags 索引为 8对应的数字是 2的所有数据 6800条
        List<ChargingData> chargingDataList = txt1.stream().filter((chargingData -> {
            String flags = chargingData.getFlags();
            if (flags.length() > 8) {
                char flag = flags.charAt(8);
                return flag == '2';
            }
            return false;
        })).filter(chargingData -> {
            String assetOperator = chargingData.getAssetOperator();
            if (orgCodes.contains(assetOperator)) {
                return true;
            }
            System.out.println(chargingData);
            return false;
        }).collect(Collectors.toList());

        System.out.println(chargingDataList.size());

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

