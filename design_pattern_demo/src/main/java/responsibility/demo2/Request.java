package responsibility.demo2;

/**
 * @author wangxing
 * @date 2021/7/30 16:52
 */
public class Request {

    private String name;
    private String reason;
    private Integer days;
    private String customInfo;
    private String groupLeaderInfo;
    private String managerInfo;
    private String departmentHeaderInfo;

    public Request(Builder builder) {
        this.name = builder.name;
        this.reason = builder.reason;
        this.days = builder.days;
        this.customInfo = builder.customInfo;
        this.groupLeaderInfo = builder.groupLeaderInfo;
        this.managerInfo = builder.managerInfo;
        this.departmentHeaderInfo = builder.departmentHeaderInfo;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public Integer getDays() {
        return days;
    }

    public String getCustomInfo() {
        return customInfo;
    }

    public String getGroupLeaderInfo() {
        return groupLeaderInfo;
    }

    public String getManagerInfo() {
        return managerInfo;
    }

    public String getDepartmentHeaderInfo() {
        return departmentHeaderInfo;
    }

    public static class Builder {
        private String name;
        private String reason;
        private Integer days;
        private String customInfo;
        private String groupLeaderInfo;
        private String managerInfo;
        private String departmentHeaderInfo;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder setDays(Integer days) {
            this.days = days;
            return this;
        }

        public Builder setCustomInfo(String customInfo) {
            this.customInfo = customInfo;
            return this;
        }

        public Builder setGroupLeaderInfo(String groupLeaderInfo) {
            this.groupLeaderInfo = groupLeaderInfo;
            return this;
        }

        public Builder setManagerInfo(String managerInfo) {
            this.managerInfo = managerInfo;
            return this;
        }

        public Builder setDepartmentHeaderInfo(String departmentHeaderInfo) {
            this.departmentHeaderInfo = departmentHeaderInfo;
            return this;
        }

        public Builder newRequest(Request request) {
            this.name = request.name;
            this.days = request.days;
            this.reason = request.reason;

            if (request.getGroupLeaderInfo() != null && !request.getGroupLeaderInfo().equals("")) {
                this.groupLeaderInfo = request.getGroupLeaderInfo();
            }
            if (request.getManagerInfo() != null && !request.getManagerInfo().equals("")) {
                this.managerInfo = request.getManagerInfo();
            }
            if (request.getDepartmentHeaderInfo() != null && !request.getDepartmentHeaderInfo().equals("")) {
                this.departmentHeaderInfo = request.getDepartmentHeaderInfo();
            }
            if (request.getCustomInfo() != null && !request.getCustomInfo().equals("")) {
                this.customInfo = request.getCustomInfo();
            }

            return this;
        }

        public Request build() {
            return new Request(this);
        }

    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", reason='" + reason + '\'' +
                ", days=" + days +
                ", customInfo='" + customInfo + '\'' +
                ", groupLeaderInfo='" + groupLeaderInfo + '\'' +
                ", managerInfo='" + managerInfo + '\'' +
                ", departmentHeaderInfo='" + departmentHeaderInfo + '\'' +
                '}';
    }
}
