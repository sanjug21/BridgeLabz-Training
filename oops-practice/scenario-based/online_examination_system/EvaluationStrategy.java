package online_examination_system;

import java.util.List;
import java.util.Map;

// Interface: Strategy for evaluating exams
public interface EvaluationStrategy {
    // Returns the calculated score based on questions and student answers
    int evaluate(List<Question> questions, Map<Integer, String> answers);
}