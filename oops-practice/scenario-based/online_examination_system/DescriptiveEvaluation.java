package online_examination_system;

import java.util.List;
import java.util.Map;

// Polymorphism: Concrete strategy for Descriptive evaluation
public class DescriptiveEvaluation implements EvaluationStrategy {
    @Override
    public int evaluate(List<Question> questions, Map<Integer, String> answers) {
        int score = 0;
        for (Question q : questions) {
            // For descriptive, we simulate grading: if an answer is provided, give full marks
            if (answers.containsKey(q.getId()) && !answers.get(q.getId()).trim().isEmpty()) {
                score += q.getMarks();
            }
        }
        return score;
    }
}