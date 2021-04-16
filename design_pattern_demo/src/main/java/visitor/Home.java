package visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/2/28 12:29
 */
public class Home {

    private List<Animal> animalList = new ArrayList<>(10);

    public void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    public void action(Person person) {
        for (Animal animal : animalList) {
            animal.accept(person);
        }
    }

}
