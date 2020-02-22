package Menu;

public class Table {
    private String field;
    private int year;
    private int nextYear;
    
    public Table(){
        this.field = "";
        this.nextYear = 0;
        this.year = 0;
    }
    
    public Table(String field, int year, int nextYear){
        this.field = field;
        this.year = year;
        this.nextYear = nextYear;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNextYear() {
        return nextYear;
    }

    public void setNextYear(int nextYear) {
        this.nextYear = nextYear;
    }
    
    
}
