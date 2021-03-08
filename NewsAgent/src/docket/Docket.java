package docket;

public class Docket {
    private int id;
    private String docketName;
    private int num;

    public Docket(String docketName, int num) throws DocketExceptionHandler {
        try {
            validateDocketName(docketName);
            validateNum(num);
        } catch (DocketExceptionHandler billExceptionHandler) {
            throw billExceptionHandler;
        }
        id = 0;
        this.docketName = docketName;
        this.num = num;
    }

    static void validateNum(int num) throws DocketExceptionHandler {
        //Agree Formating Rules on "Docket num"
        //E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
        if (num<0) {
            throw new DocketExceptionHandler("Docket num Can't be minus");
        } else if (num>1000000) {
            throw new DocketExceptionHandler("Docket num can't bigger than 1000000");
        }
    }

    public static void validateDocketName(String name) throws DocketExceptionHandler {
        //Agree Formating Rules on "Biling Name"
        //E.G. Name String must be a minimum of 2 characters and a maximum of 50 characters
        if (name.length() == 0 || name.isEmpty()) {
            throw new DocketExceptionHandler("Docket Name NOT specified");
        } else if (name.length() < 3) {
            throw new DocketExceptionHandler("Docket Name does not meet minimum length requirements");
        } else if (name.length() > 50) {
            throw new DocketExceptionHandler("Docket Name exceeds maximum length requirements");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocketName() {
        return docketName;
    }

    public void setDocketName(String docketName) {
        this.docketName = docketName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
