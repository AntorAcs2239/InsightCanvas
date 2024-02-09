package Models;

public class SentimentResponse {
    private String sentiment;
    double positive_percentage,negative_percentage,neutral_percentage;

    public SentimentResponse(String sentiment, double positive_percentage, double negative_percentage, double neutral_percentage) {
        this.sentiment = sentiment;
        this.positive_percentage = positive_percentage;
        this.negative_percentage = negative_percentage;
        this.neutral_percentage = neutral_percentage;
    }

    public SentimentResponse() {
    }

    public String getSentiment() {
        return sentiment;
    }
    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public double getPositive_percentage() {
        return positive_percentage;
    }

    public void setPositive_percentage(double positive_percentage) {
        this.positive_percentage = positive_percentage;
    }

    public double getNegative_percentage() {
        return negative_percentage;
    }

    public void setNegative_percentage(double negative_percentage) {
        this.negative_percentage = negative_percentage;
    }

    public double getNeutral_percentage() {
        return neutral_percentage;
    }

    public void setNeutral_percentage(double neutral_percentage) {
        this.neutral_percentage = neutral_percentage;
    }
}
