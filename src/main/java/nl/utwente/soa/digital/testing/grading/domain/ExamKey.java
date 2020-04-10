package nl.utwente.soa.digital.testing.grading.domain;

public class ExamKey {

    private Integer e_id;
    private String s_id;

    public ExamKey(Integer e_id, String s_id) {
        this.e_id = e_id;
        this.s_id = s_id;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    @Override
    public boolean equals(Object o) {
        if(o != null && o instanceof ExamKey) {
            return (((ExamKey) o).getE_id().equals(this.e_id) && ((ExamKey) o).getS_id().equals(this.s_id));
        }
        return false;
    }
}
