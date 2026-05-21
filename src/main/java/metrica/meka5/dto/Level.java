package metrica.meka5.dto;


public class Level {

    private Long id;
    private boolean uppercase;
    private boolean numbers;
    private boolean punctuation;

    public Level() {}

    public Level(Long id, boolean uppercase, boolean numbers, boolean punctuation) {
        this.id = id;
        this.uppercase = uppercase;
        this.numbers = numbers;
        this.punctuation = punctuation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public boolean isNumbers() {
        return numbers;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public boolean isPunctuation() {
        return punctuation;
    }

    public void setPunctuation(boolean punctuation) {
        this.punctuation = punctuation;
    }
}
