package ru.skillsad.sad.service;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skillsad.sad.domain.catalog.Category;
import ru.skillsad.sad.domain.catalog.Product;
import ru.skillsad.sad.domain.catalog.SubCategory;
import ru.skillsad.sad.domain.general.MainText;
import ru.skillsad.sad.domain.user.Roles;
import ru.skillsad.sad.domain.user.User;
import ru.skillsad.sad.repository.catalog.CategoryRepo;
import ru.skillsad.sad.repository.MainTextRepo;
import ru.skillsad.sad.repository.catalog.SubCategoryRepo;
import ru.skillsad.sad.repository.UserRepo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Collections;

@Component
@AllArgsConstructor
public class CommandLineAppStartupRunner {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final MainTextRepo mainTextRepo;
    private final CategoryRepo categoryRepo;
    private final SubCategoryRepo subCategoryRepo;
    private final String MAIN_TEXT = "Но дальнейшее развитие различных форм деятельности представляет собой интересный эксперимент проверки своевременного выполнения сверхзадачи. Ясность нашей позиции очевидна: укрепление и развитие внутренней структуры требует определения и уточнения переосмысления внешнеэкономических политик. Прежде всего, существующая теория играет важную роль в формировании глубокомысленных рассуждений. Высокий уровень вовлечения представителей целевой аудитории является четким доказательством простого факта: выбранный нами инновационный путь не оставляет шанса для инновационных методов управления процессами. Каждый из нас понимает очевидную вещь: глубокий уровень погружения играет важную роль в формировании форм воздействия. Приятно, граждане, наблюдать, как диаграммы связей лишь добавляют фракционных разногласий и смешаны с не уникальными данными до степени совершенной неузнаваемости, из-за чего возрастает их статус бесполезности. А также стремящиеся вытеснить традиционное производство, нанотехнологии набирают популярность среди определенных слоев населения, а значит, должны быть призваны к ответу. Также как сплочённость команды профессионалов предоставляет широкие возможности для приоритизации разума над эмоциями.";

    public void runUser() {
        if (!userRepo.findByName("Ad").isPresent()) {
            User user = new User("Ad", passwordEncoder.encode("12"));
            user.setRoles(Collections.singleton(Roles.ROLE_ADMIN));
            userRepo.save(user);
        }
    }

    public void run() throws Exception {
        if (mainTextRepo.getById(1l) == null) {
            mainTextRepo.save(new MainText(MAIN_TEXT));
        }
        if (!categoryRepo.findByName("Груши").isPresent()) {
            BufferedImage bImage = ImageIO.read(new ClassPathResource("CatWarrior.jpg").getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] bytes = bos.toByteArray();

            Category category = new Category("Груши");
            SubCategory subCategory = new SubCategory("Груши-золотые");

            for (int i = 0; i < 20; i++) {
                Product product = new Product("Груша", "Описание груши", (byte) 40, "1.jpg", bytes);
                subCategory.addProduct(product);
            }
            category.addSubCategory(subCategory);
            categoryRepo.save(category);
        }
    }
}
