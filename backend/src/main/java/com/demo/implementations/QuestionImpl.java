package com.demo.implementations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.demo.dto.QuestionGet;
import com.demo.dto.Token;
import com.demo.model.Question;
import com.demo.model.Space;
import com.demo.model.User;
import com.demo.repositories.QuestionRepository;
import com.demo.repositories.SpaceRepository;
import com.demo.repositories.UserRepository;
import com.demo.services.QuestionService;

public class QuestionImpl implements QuestionService {

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    PermissionImpl permissionImpl;

    @Override
    public Question createNewQuestion(Token token, String questionText, long spaceId) {

        Space space = spaceRepository.findById(spaceId);
        
        Optional<User> findUser = userRepository.findById(token.getId());

        User user = findUser.get();

        if (space == null) {
            System.out.println("O espaço está nulo!");
            return null; 
        }

        System.out.println("Space ID: " + space.getId());

        var permission = permissionImpl.validatePermission(user.getId(), spaceId);
        System.out.println("Permission: " + permission);

        if (permission == 0) {
            System.out.println("Permissão inválida!");
            return null;
        }
            
        Question question = new Question();
        question.setQuestion(questionText);
        question.setSpace(space);
        question.setUser(user);
        question.setPermission(permission);
    
        return question;
}


    @Override
    public boolean deleteQuestion(long idQuestion) {

        Question question = questionRepository.findById(idQuestion);

        if (question == null) {
            return false; 
        }

        questionRepository.delete(question);
        questionRepository.save(question);
        
        return true; 
    }

    @Override
    public Question getQuestionById(long id) {

    Question question = questionRepository.findById(id);

    return question;
    }

    // @Override
    // public Page<Question> getQuestion(Long space, int page, int size) {

    //     Pageable pageable = PageRequest.of(page, size);
    //     return questionRepository.searchQuestion(space, pageable);
    // }


    @Override
    public ArrayList<QuestionGet> getQuestion(Long spaceId, Integer page, Integer limit) {
        System.out.println("Os mano espaço, pagina e limite: " + spaceId + page + limit);
        // findByNameContains: procura no banco por NOMES que CONTENHAM (%LIKE%) o parâmetro name
        var results = questionRepository.findBySpaceId(spaceId, PageRequest.of(page, limit));

        String spaceName = spaceRepository.findById(spaceId).get().getTitle();

        ArrayList<QuestionGet> list = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            list.add(new QuestionGet(results.get(i).getQuestion(), results.get(i).getId(), results.get(i).getUser().getId(), results.get(i).getUser().getName(), spaceName));
        }

        if (list.size() == 0) {
            list.add(new QuestionGet(null, null, null, null, spaceName));
        }

        // var resultado = spaceRepository.findAll(PageRequest.of(page, limit));
        System.out.println(list);
        
        return list;
    }

    
}
