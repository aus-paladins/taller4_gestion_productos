package aus.t4.paladins.gestorback.config;

import aus.t4.paladins.gestorback.model.Atributo;
import aus.t4.paladins.gestorback.model.Categoria;
import aus.t4.paladins.gestorback.model.Departamento;
import aus.t4.paladins.gestorback.model.Producto;
import aus.t4.paladins.gestorback.model.ValorAtributo;
import aus.t4.paladins.gestorback.model.VarianteProducto;
import aus.t4.paladins.gestorback.repository.AtributoRepository;
import aus.t4.paladins.gestorback.repository.CategoriaRepository;
import aus.t4.paladins.gestorback.repository.DepartamentoRepository;
import aus.t4.paladins.gestorback.repository.ProductoRepository;
import aus.t4.paladins.gestorback.repository.ValorAtributoRepository;
import aus.t4.paladins.gestorback.repository.VarianteProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

  @Bean
  CommandLineRunner initData(
      DepartamentoRepository departamentoRepository,
      CategoriaRepository categoriaRepository,
      ProductoRepository productoRepository,
      AtributoRepository atributoRepository,
      ValorAtributoRepository valorAtributoRepository,
      VarianteProductoRepository varianteProductoRepository) {

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

      celulares = categoriaRepository.save(celulares);
      computacion = categoriaRepository.save(computacion);
      accesorios = categoriaRepository.save(accesorios);

      Producto iphone = new Producto();
      iphone.setNombre("iPhone 15");
      iphone.setDescription("Smartphone Apple, 128GB");
      iphone.setPrecioBase(new BigDecimal("999999.00"));
      iphone.setActivo(true);
      iphone.setCategoria(celulares);

      Producto notebook = new Producto();
      notebook.setNombre("Notebook Lenovo IdeaPad");
      notebook.setDescription("14\", 8GB RAM, 512GB SSD");
      notebook.setPrecioBase(new BigDecimal("750000.00"));
      notebook.setActivo(true);
      notebook.setCategoria(computacion);

      Producto auriculares = new Producto();
      auriculares.setNombre("Auriculares Bluetooth");
      auriculares.setDescription("Inalámbricos, cancelación de ruido");
      auriculares.setPrecioBase(new BigDecimal("45000.00"));
      auriculares.setActivo(true);
      auriculares.setCategoria(accesorios);

      iphone = productoRepository.save(iphone);
      notebook = productoRepository.save(notebook);
      productoRepository.save(auriculares);

      Departamento indumentaria = new Departamento();
      indumentaria.setNombre("Indumentaria");
      indumentaria = departamentoRepository.save(indumentaria);

      Categoria abrigos = new Categoria();
      abrigos.setNombre("Abrigos");
      abrigos.setDepartamento(indumentaria);
      abrigos = categoriaRepository.save(abrigos);

      Producto campera = new Producto();
      campera.setNombre("Campera Térmica");
      campera.setDescription("Campera térmica para invierno");
      campera.setPrecioBase(new BigDecimal("120000.00"));
      campera.setActivo(true);
      campera.setCategoria(abrigos);
      campera = productoRepository.save(campera);

      Atributo talle = new Atributo();
      talle.setNombre("Talle");
      talle = atributoRepository.save(talle);

      Atributo color = new Atributo();
      color.setNombre("Color");
      color = atributoRepository.save(color);

      ValorAtributo talleL = new ValorAtributo();
      talleL.setValor("L");
      talleL.setAtributo(talle);

      ValorAtributo talleM = new ValorAtributo();
      talleM.setValor("M");
      talleM.setAtributo(talle);

      ValorAtributo colorNegro = new ValorAtributo();
      colorNegro.setValor("Negro");
      colorNegro.setAtributo(color);

      ValorAtributo colorAzul = new ValorAtributo();
      colorAzul.setValor("Azul");
      colorAzul.setAtributo(color);

      talleL = valorAtributoRepository.save(talleL);
      talleM = valorAtributoRepository.save(talleM);
      colorNegro = valorAtributoRepository.save(colorNegro);
      colorAzul = valorAtributoRepository.save(colorAzul);

      VarianteProducto camperaLNegra = new VarianteProducto();
      camperaLNegra.setSku("CMP-T-L-NG");
      camperaLNegra.setStock(15);
      camperaLNegra.setPrecioExtra(BigDecimal.ZERO);
      camperaLNegra.setProducto(campera);
      camperaLNegra.setValoresAtributo(List.of(talleL, colorNegro));

      VarianteProducto camperaMAzul = new VarianteProducto();
      camperaMAzul.setSku("CMP-T-M-AZ");
      camperaMAzul.setStock(2);
      camperaMAzul.setPrecioExtra(BigDecimal.ZERO);
      camperaMAzul.setProducto(campera);
      camperaMAzul.setValoresAtributo(List.of(talleM, colorAzul));

      varianteProductoRepository.save(camperaLNegra);
      varianteProductoRepository.save(camperaMAzul);

      // --- Atributo extra para Electrónica ---
      Atributo almacenamiento = new Atributo();
      almacenamiento.setNombre("Almacenamiento");
      almacenamiento = atributoRepository.save(almacenamiento);

      ValorAtributo gb128 = new ValorAtributo();
      gb128.setValor("128GB");
      gb128.setAtributo(almacenamiento);
      gb128 = valorAtributoRepository.save(gb128);

      ValorAtributo gb256 = new ValorAtributo();
      gb256.setValor("256GB");
      gb256.setAtributo(almacenamiento);
      gb256 = valorAtributoRepository.save(gb256);

      // --- Variante 1: iPhone 15 (128GB - Negro) ---
      VarianteProducto iphone128Negro = new VarianteProducto();
      iphone128Negro.setSku("IPH15-128-NG");
      iphone128Negro.setStock(10);
      iphone128Negro.setPrecioExtra(BigDecimal.ZERO);
      iphone128Negro.setProducto(iphone);
      iphone128Negro.setValoresAtributo(List.of(gb128, colorNegro));

      // --- Variante 2: iPhone 15 (256GB - Azul) con costo extra ---
      VarianteProducto iphone256Azul = new VarianteProducto();
      iphone256Azul.setSku("IPH15-256-AZ");
      iphone256Azul.setStock(4);
      iphone256Azul.setPrecioExtra(new BigDecimal("150000.00")); // Recargo por mayor capacidad
      iphone256Azul.setProducto(iphone);
      iphone256Azul.setValoresAtributo(List.of(gb256, colorAzul));

      // --- Variante 3: Notebook Lenovo IdeaPad (Negra) ---
      VarianteProducto notebookNegra = new VarianteProducto();
      notebookNegra.setSku("NB-LEN-512-NG");
      notebookNegra.setStock(6);
      notebookNegra.setPrecioExtra(BigDecimal.ZERO);
      notebookNegra.setProducto(notebook);
      notebookNegra.setValoresAtributo(List.of(colorNegro));

      // Guardar las variantes
      varianteProductoRepository.save(iphone128Negro);
      varianteProductoRepository.save(iphone256Azul);
      varianteProductoRepository.save(notebookNegra);
    };
  }
}
// Inicializa datos de prueba
