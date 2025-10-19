package org.skypro.exam.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    public JavaQuestionService() {
        addQuestion(new Question("Что такое JDK?", "Java Development Kit"));
        addQuestion(new Question("Что такое ArrayList?", "Это класс, реализующий интерфейс List, предоставляющий динамический массив."));
        addQuestion(new Question("Чем отличается HashMap от TreeMap?", "HashMap — неупорядоченный доступ, быстрый. TreeMap — отсортированный по ключам, медленнее."));
    }

    @Override
    public void addQuestion(String subject, Question question) {
        if ("Java".equalsIgnoreCase(subject) && question != null) {
            questions.add(question);
        }
    }

    @Override
    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }
    }

    @Override
    public Question findQuestion(String question, String answer) {
        for (Question q : questions) {
            if (q.getQuestion().equalsIgnoreCase(question) && q.getAnswer().equalsIgnoreCase(answer)) {
                return q;
            }
        }
        return null;
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }

    @Override
    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }
}