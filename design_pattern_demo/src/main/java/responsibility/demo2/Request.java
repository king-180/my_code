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
    private String groupLeaderMsg;
    private String managerMsg;
    private String departmentHeaderMsg;

    public Request(Builder builder) {
        this.name = builder.name;
        this.reason = builder.reason;
        this.days = builder.days;
        this.customInfo = builder.customInfo;
        this.groupLeaderMsg = builder.groupLeaderMsg;
        this.managerMsg = builder.managerMsg;
        this.departmentHeaderMsg = builder.departmentHeaderMsg;
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

    public String getGroupLeaderMsg() {
        return groupLeaderMsg;
    }

    public String getManagerMsg() {
        return managerMsg;
    }

    public String getDepartmentHeaderMsg() {
        return departmentHeaderMsg;
    }

    public static class Builder {
        private String name;
        private String reason;
        private Integer days;
        private String customInfo;
        private String groupLeaderMsg;
        private String managerMsg;
        private String departmentHeaderMsg;

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

        public Builder setGroupLeaderMsg(String groupLeaderMsg) {
            this.groupLeaderMsg = groupLeaderMsg;
            return this;
        }

        public Builder setManagerMsg(String managerMsg) {
            this.managerMsg = managerMsg;
            return this;
        }

        public Builder setDepartmentHeaderMsg(String departmentHeaderMsg) {
            this.departmentHeaderMsg = departmentHeaderMsg;
            return this;
        }

        public Builder newRequest(Request request) {
            this.name = request.name;
            this.days = request.days;
            this.reason = request.reason;

            if (request.getGroupLeaderMsg() != null && !request.getGroupLeaderMsg().equals("")) {
                this.groupLeaderMsg = request.getGroupLeaderMsg();
            }
            if (request.getManagerMsg() != null && !request.getManagerMsg().equals("")) {
                this.managerMsg = request.getManagerMsg();
            }
            if (request.getDepartmentHeaderMsg() != null && !request.getDepartmentHeaderMsg().equals("")) {
                this.departmentHeaderMsg = request.getDepartmentHeaderMsg();
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
                ", groupLeaderMsg='" + groupLeaderMsg + '\'' +
                ", managerMsg='" + managerMsg + '\'' +
                ", departmentHeaderMsg='" + departmentHeaderMsg + '\'' +
                '}';
    }
}
