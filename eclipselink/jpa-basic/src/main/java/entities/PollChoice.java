package entities;

import javax.persistence.*;

@Entity
public class PollChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String choiceText;

    private int votes;

    public Long getId() {
        return id;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "PollChoice{" +
                "id=" + id +
                ", choiceText='" + choiceText + '\'' +
                ", votes=" + votes +
                '}';
    }
}
