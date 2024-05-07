package com.example.project1.service;

import com.example.project1.models.PostCodes;
import com.example.project1.repositories.PostCodesRepository;
import com.example.project1.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostCodesService {

    @Autowired
    private PostCodesRepository postCodesRepository;

    @Autowired
    private Cache cache;

    // Создание нового почтового кода
    public PostCodes createPostCodes(PostCodes postCodes) {
        return postCodesRepository.save(postCodes);
    }

    // Получение списка всех почтовых кодов
    public List<PostCodes> getAllPostCodes() {
        return postCodesRepository.findAll();
    }

    // Получение почтового кода по ID
    public Optional<PostCodes> getPostCodesById(Long id) {
        if (cache.containsPostCodes(id)) {
            return Optional.of(cache.getPostCodes(id));
        } else {
            return postCodesRepository.findById(id);
        }
    }

    // Обновление информации о почтовом коде
    public PostCodes updatePostCodes(Long id, PostCodes newPostCodes) {
        // Перед обновлением почтового кода, удалим его из кэша
        cache.removePostCodes(id);
        // Обновляем почтовый код в базе данных
        return postCodesRepository.findById(id)
                .map(postCodes -> {
                    // Обновляем информацию о почтовом коде
                    postCodes.setPostCodes(newPostCodes.getPostCodes());
                    postCodes.setTown(newPostCodes.getTown());
                    postCodes.setState(newPostCodes.getState());
                    // Сохраняем обновленный почтовый код в базе данных
                    return postCodesRepository.save(postCodes);
                })
                .orElseGet(() -> {
                    newPostCodes.setId(id);
                    // Создаем новый почтовый код, если не найден существующий по заданному id
                    return postCodesRepository.save(newPostCodes);
                });
    }

    // Удаление почтового кода
    public void deletePostCodes(Long id) {
        // Перед удалением почтового кода, удалим его из кэша
        cache.removePostCodes(id);
        // Удаляем почтовый код из базы данных
        postCodesRepository.deleteById(id);
    }
}
