package ogrg.zerock;

public class TagoDTO {
    private String train_no;
    private String dep_place;
    private String arr_place;
    private String dep_time;
    private String arr_time;

    public String getTrainNo() {
        return train_no;
    }

    public void setTrainNo(String trainNo) {
        this.train_no = trainNo;
    }

    public String getDepPlace() {
        return dep_place;
    }

    public void setDepPlace(String depPlace) {
        this.dep_place = depPlace;
    }

    public String getArrPlace() {
        return arr_place;
    }

    public void setArrPlace(String arrPlace) {
        this.arr_place = arrPlace;
    }

    public String getDepTime() {
        return dep_time;
    }

    public void setDepTime(String depTime) {
        this.dep_time = depTime;
    }

    public String getArrTime() {
        return arr_time;
    }

    public void setArrTime(String arrTime) {
        this.arr_time = arrTime;
    }
}
