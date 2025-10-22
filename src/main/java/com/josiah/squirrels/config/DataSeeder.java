package com.josiah.squirrels.config;

import com.josiah.squirrels.item.entity.Item;
import com.josiah.squirrels.item.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner initDatabase(ItemRepository itemRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (itemRepo.count() == 0) {
                    itemRepo.save(new Item("Acorn", "A crunchy oak nut packed with energy — perfect for winter storage."));
                    itemRepo.save(new Item("Pinecone", "A scaly pine treasure filled with hidden seeds inside."));
                    itemRepo.save(new Item("Walnut", "A tough shell but worth the effort for the rich nut inside."));
                    itemRepo.save(new Item("Maple Seed", "A light, twirling seed that’s easy to carry and snack on."));
                    itemRepo.save(new Item("Chestnut", "Smooth and shiny — a favorite autumn treat for any squirrel."));
                    itemRepo.save(new Item("Berry Mix", "A colorful assortment of forest berries, sweet and juicy."));
                    itemRepo.save(new Item("Mushroom Cap", "Soft and savory, found under the shade of tall trees."));
                    itemRepo.save(new Item("Hazelnut", "A small round nut with a mild, buttery flavor."));
                    itemRepo.save(new Item("Corn Kernel", "Dried golden kernels, crunchy and filling for long trips."));
                    itemRepo.save(new Item("Sunflower Seed", "Tiny but full of flavor and fat — a top choice for energy."));
                }
            }
        };
    }
}

