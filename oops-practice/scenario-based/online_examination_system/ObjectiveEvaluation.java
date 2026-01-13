package online_examination_system;

import java.util.List;
import java.util.Map;

// Polymorphism: Concrete strategy for Objective (Multiple Choice) evaluation
public class ObjectiveEvaluation implements EvaluationStrategy {
    @Override
    public int evaluate(List<Question> questions, Map<Integer, String> answers) {
        int score = 0;
        for (Question q : questions) {
            if (answers.containsKey(q.getId())) {
                // Exact match required for objective questions
                if (answers.get(q.getId()).equalsIgnoreCase(q.getCorrectAnswer())) {
                    score += q.getMarks();
                }
            }
        }
        return score;
    }
}