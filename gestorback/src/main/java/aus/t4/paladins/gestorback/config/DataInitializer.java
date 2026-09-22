package aus.t4.paladins.gestorback.config;

import aus.t4.paladins.gestorback.model.Categoria;
import aus.t4.paladins.gestorback.model.Departamento;
import aus.t4.paladins.gestorback.repository.CategoriaRepository;
import aus.t4.paladins.gestorback.repository.DepartamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            DepartamentoRepository departamentoRepository,
            CategoriaRepository categoriaRepository) {

        return args -> {

            if (departamentoRepository.count() > 0) {
                return;
            }

            Departamento electronica = new Departamento();
            electronica.setNombre("Electrónica");

            electronica = departamentoRepository.save(electronica);

            Categoria celulares = new Categoria();
            celulares.setNombre("Celulares");
            celulares.setDepartamento(electronica);

            Categoria computacion = new Categoria();
            computacion.setNombre("Computación");
            computacion.setDepartamento(electronica);

            Categoria accesorios = new Categoria();
            accesorios.setNombre("Accesorios");
            accesorios.setDepartamento(electronica);

            categoriaRepository.save(celulares);
            categoriaRepository.save(computacion);
            categoriaRepository.save(accesorios);
        };
    }
}
//Inicializa datos de prueba