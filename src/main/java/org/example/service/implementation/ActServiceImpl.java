package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.ActDaoImpl;
import org.example.model.Act;
import org.example.service.api.ActService;

import java.util.List;

public class ActServiceImpl implements ActService {


    private final IDao<Act,Long > dao = new ActDaoImpl();
//    private final IDao<Act,String > dao = new ActImpl();

    @Override
    public Act addAct(Act act) {
        try{
            Act a= dao.add(act);
            System.out.println("Act Added Seccessfully");
            return a ;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Act getAct(Long id) {
        try{
            System.out.println("This Act is Found");
            return dao.getById(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Act updateAct(Act act) {
        try{
            dao.update(act);
            System.out.println("Act Updated Successfully");
            return act ;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteAct(Act act) {
            try{
                dao.delete(act);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
    }

    @Override
    public List<Act> getActs() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
