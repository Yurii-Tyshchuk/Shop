package ru.skillsad.sad.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.domain.general.MainText;
import ru.skillsad.sad.domain.user.Role;
import ru.skillsad.sad.domain.user.User;
import ru.skillsad.sad.repository.CategoryRepo;
import ru.skillsad.sad.repository.MainTextRepo;
import ru.skillsad.sad.repository.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final MainTextRepo mainTextRepo;
    private final CategoryRepo categoryRepo;
    private final String MAIN_TEXT = "Но дальнейшее развитие различных форм деятельности представляет собой интересный эксперимент проверки своевременного выполнения сверхзадачи. Ясность нашей позиции очевидна: укрепление и развитие внутренней структуры требует определения и уточнения переосмысления внешнеэкономических политик. Прежде всего, существующая теория играет важную роль в формировании глубокомысленных рассуждений. Высокий уровень вовлечения представителей целевой аудитории является четким доказательством простого факта: выбранный нами инновационный путь не оставляет шанса для инновационных методов управления процессами. Каждый из нас понимает очевидную вещь: глубокий уровень погружения играет важную роль в формировании форм воздействия. Приятно, граждане, наблюдать, как диаграммы связей лишь добавляют фракционных разногласий и смешаны с не уникальными данными до степени совершенной неузнаваемости, из-за чего возрастает их статус бесполезности. А также стремящиеся вытеснить традиционное производство, нанотехнологии набирают популярность среди определенных слоев населения, а значит, должны быть призваны к ответу. Также как сплочённость команды профессионалов предоставляет широкие возможности для приоритизации разума над эмоциями.";

    public CommandLineAppStartupRunner(UserRepo userRepo, PasswordEncoder passwordEncoder, MainTextRepo mainTextRepo, CategoryRepo categoryRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.mainTextRepo = mainTextRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByName("Ad") == null) {

            User user = new User("Ad", passwordEncoder.encode("12"));
            user.setRoles(Collections.singleton(Role.ADMIN));
            userRepo.save(user);
        }
        if (mainTextRepo.getById(1l) == null) {
            mainTextRepo.save(new MainText(MAIN_TEXT));
        }
        //        if (productRepo.getById(1L) == null) {
//
//            BufferedImage bImage = ImageIO.read(new FileInputStream("CatWarrior.jpg"));
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos);
//            byte[] bytes = bos.toByteArray();
//
//            Product product = new Product("Груша", "Описание груши",
//                    new SubCategory("ПодкатегорияГруши",
//                            new Category("Категория груши")), (byte) 4,"1.jpg", bytes);
//            productRepo.save(product);
//        }

    }
}
