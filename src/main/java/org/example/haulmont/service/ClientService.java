package org.example.haulmont.service;

import org.example.haulmont.domain.Client;
import org.example.haulmont.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private BankService bankService;



    public void addClient(Client client) {
        clientRepo.save(client);
        bankService.addClient(client);
    }

    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    public List<Client> findByFilter(String surname, String name, String patronymic, String email, String phone, String passportId) {
        Set<Client> result = null;


        //если параметр surname не null и не пуст, то ищем совпадения в БД
        if (surname != null && !surname.trim().isEmpty()) {
            result = clientRepo.findAllBySurname(surname.trim());
        }

        //если параметр name не null и не пуст
        if (name != null && !name.trim().isEmpty()) {

            //если result null (это первое переданное поле), то ищем совпадения в бд
            if (result == null) {
                result = clientRepo.findAllByName(name.trim());

                //если result не null

            } else {

                //то пересекаем его с множеством, которое получили при поиске по полю name
                result.retainAll(clientRepo.findAllByName(name.trim()));
            }
        }

        //если параметр patronymic не null и не пуст
        if (patronymic != null && !patronymic.trim().isEmpty()) {

            //если result null (это первое переданное поле), то ищем совпадения в бд
            if (result == null) {
                result = clientRepo.findAllByPatronymic(patronymic.trim());

                //если result не null
            } else {

                //то пересекаем его с множеством, которое получили при поиске по полю patronymic
                result.retainAll(clientRepo.findAllByPatronymic(patronymic.trim()));
            }
        }

        //если параметр email не null и не пуст
        if (email != null && !email.trim().isEmpty()) {

            //если result null (это первое переданное поле), то ищем совпадения в бд
            if (result == null) {
                result = clientRepo.findAllByEmail(email.trim());

                //если result не null
            } else {

                //то пересекаем его с множеством, котороеполучили при поиске по полю email
                result.retainAll(clientRepo.findAllByEmail(email.trim()));
            }
        }

        //если параметр phone не null и не пуст
        if (phone != null && !phone.trim().isEmpty()) {

            //если result null (это первое переданное поле), то ищем совпадения в бд
            if (result == null) {
                result = clientRepo.findAllByPhone(phone.trim());

                //если result не null
            } else {

                //то пересекаем его с множеством, котороеполучили при поиске по полю phone
                result.retainAll(clientRepo.findAllByPhone(phone.trim()));
            }
        }

        //если параметр passportId не null и не пуст
        if (passportId != null && !passportId.trim().isEmpty()) {

            //если result null (это первое переданное поле), то ищем совпадения в бд
            if (result == null) {
                result = clientRepo.findAllByPassportId(passportId.trim());

                //если result не null
            } else {

                //то пересекаем его с множеством, котороеполучили при поиске по полю phone
                result.retainAll(clientRepo.findAllByPassportId(passportId.trim()));
            }
        }


        //если ни одно поле фильтра не было передано, то выводим всех клиентов
        if(result == null)
            return  clientRepo.findAll();

        //если result не null, то возвращаем клиентов листом
        return  new ArrayList<>(result);
    }
}
