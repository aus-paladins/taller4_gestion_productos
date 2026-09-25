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

      // Si ya existen datos, no volvemos a cargarlos.
      if (departamentoRepository.count() > 0) {
        return;
      }

      // =========================================================
      // DEPARTAMENTOS
      // =========================================================

      Departamento electronica = new Departamento();
      electronica.setNombre("Electrónica");
      electronica = departamentoRepository.save(electronica);

      Departamento indumentaria = new Departamento();
      indumentaria.setNombre("Indumentaria");
      indumentaria = departamentoRepository.save(indumentaria);

      Departamento hogar = new Departamento();
      hogar.setNombre("Hogar");
      hogar = departamentoRepository.save(hogar);


      // =========================================================
      // CATEGORÍAS
      // =========================================================

      // -------------------------
      // Electrónica
      // -------------------------

      Categoria celulares = new Categoria();
      celulares.setNombre("Celulares");
      celulares.setDepartamento(electronica);

      Categoria computacion = new Categoria();
      computacion.setNombre("Computación");
      computacion.setDepartamento(electronica);

      Categoria audio = new Categoria();
      audio.setNombre("Audio");
      audio.setDepartamento(electronica);

      celulares = categoriaRepository.save(celulares);
      computacion = categoriaRepository.save(computacion);
      audio = categoriaRepository.save(audio);


      // -------------------------
      // Indumentaria
      // -------------------------

      Categoria abrigos = new Categoria();
      abrigos.setNombre("Abrigos");
      abrigos.setDepartamento(indumentaria);

      Categoria remeras = new Categoria();
      remeras.setNombre("Remeras");
      remeras.setDepartamento(indumentaria);

      Categoria pantalones = new Categoria();
      pantalones.setNombre("Pantalones");
      pantalones.setDepartamento(indumentaria);

      abrigos = categoriaRepository.save(abrigos);
      remeras = categoriaRepository.save(remeras);
      pantalones = categoriaRepository.save(pantalones);


      // -------------------------
      // Hogar
      // -------------------------

      Categoria muebles = new Categoria();
      muebles.setNombre("Muebles");
      muebles.setDepartamento(hogar);

      Categoria cocina = new Categoria();
      cocina.setNombre("Cocina");
      cocina.setDepartamento(hogar);

      Categoria iluminacion = new Categoria();
      iluminacion.setNombre("Iluminación");
      iluminacion.setDepartamento(hogar);

      muebles = categoriaRepository.save(muebles);
      cocina = categoriaRepository.save(cocina);
      iluminacion = categoriaRepository.save(iluminacion);


      // =========================================================
      // ATRIBUTOS
      // =========================================================

      Atributo talle = new Atributo();
      talle.setNombre("Talle");
      talle = atributoRepository.save(talle);

      Atributo color = new Atributo();
      color.setNombre("Color");
      color = atributoRepository.save(color);

      Atributo almacenamiento = new Atributo();
      almacenamiento.setNombre("Almacenamiento");
      almacenamiento = atributoRepository.save(almacenamiento);

      Atributo capacidad = new Atributo();
      capacidad.setNombre("Capacidad");
      capacidad = atributoRepository.save(capacidad);

      Atributo material = new Atributo();
      material.setNombre("Material");
      material = atributoRepository.save(material);


      // =========================================================
      // VALORES DE ATRIBUTOS
      // =========================================================

      // -------------------------
      // Talle
      // -------------------------

      ValorAtributo talleS = new ValorAtributo();
      talleS.setValor("S");
      talleS.setAtributo(talle);

      ValorAtributo talleM = new ValorAtributo();
      talleM.setValor("M");
      talleM.setAtributo(talle);

      ValorAtributo talleL = new ValorAtributo();
      talleL.setValor("L");
      talleL.setAtributo(talle);

      ValorAtributo talleXL = new ValorAtributo();
      talleXL.setValor("XL");
      talleXL.setAtributo(talle);

      talleS = valorAtributoRepository.save(talleS);
      talleM = valorAtributoRepository.save(talleM);
      talleL = valorAtributoRepository.save(talleL);
      talleXL = valorAtributoRepository.save(talleXL);


      // -------------------------
      // Color
      // -------------------------

      ValorAtributo colorNegro = new ValorAtributo();
      colorNegro.setValor("Negro");
      colorNegro.setAtributo(color);

      ValorAtributo colorBlanco = new ValorAtributo();
      colorBlanco.setValor("Blanco");
      colorBlanco.setAtributo(color);

      ValorAtributo colorAzul = new ValorAtributo();
      colorAzul.setValor("Azul");
      colorAzul.setAtributo(color);

      ValorAtributo colorRojo = new ValorAtributo();
      colorRojo.setValor("Rojo");
      colorRojo.setAtributo(color);

      colorNegro = valorAtributoRepository.save(colorNegro);
      colorBlanco = valorAtributoRepository.save(colorBlanco);
      colorAzul = valorAtributoRepository.save(colorAzul);
      colorRojo = valorAtributoRepository.save(colorRojo);


      // -------------------------
      // Almacenamiento
      // -------------------------

      ValorAtributo almacenamiento128 = new ValorAtributo();
      almacenamiento128.setValor("128GB");
      almacenamiento128.setAtributo(almacenamiento);

      ValorAtributo almacenamiento256 = new ValorAtributo();
      almacenamiento256.setValor("256GB");
      almacenamiento256.setAtributo(almacenamiento);

      ValorAtributo almacenamiento512 = new ValorAtributo();
      almacenamiento512.setValor("512GB");
      almacenamiento512.setAtributo(almacenamiento);

      almacenamiento128 =
          valorAtributoRepository.save(almacenamiento128);

      almacenamiento256 =
          valorAtributoRepository.save(almacenamiento256);

      almacenamiento512 =
          valorAtributoRepository.save(almacenamiento512);


      // -------------------------
      // Capacidad
      // -------------------------

      ValorAtributo capacidad500 = new ValorAtributo();
      capacidad500.setValor("500 ml");
      capacidad500.setAtributo(capacidad);

      ValorAtributo capacidad1L = new ValorAtributo();
      capacidad1L.setValor("1 litro");
      capacidad1L.setAtributo(capacidad);

      ValorAtributo capacidad2L = new ValorAtributo();
      capacidad2L.setValor("2 litros");
      capacidad2L.setAtributo(capacidad);

      capacidad500 = valorAtributoRepository.save(capacidad500);
      capacidad1L = valorAtributoRepository.save(capacidad1L);
      capacidad2L = valorAtributoRepository.save(capacidad2L);


      // -------------------------
      // Material
      // -------------------------

      ValorAtributo materialAlgodon = new ValorAtributo();
      materialAlgodon.setValor("Algodón");
      materialAlgodon.setAtributo(material);

      ValorAtributo materialAcero = new ValorAtributo();
      materialAcero.setValor("Acero inoxidable");
      materialAcero.setAtributo(material);

      ValorAtributo materialMadera = new ValorAtributo();
      materialMadera.setValor("Madera");
      materialMadera.setAtributo(material);

      materialAlgodon = valorAtributoRepository.save(materialAlgodon);
      materialAcero = valorAtributoRepository.save(materialAcero);
      materialMadera = valorAtributoRepository.save(materialMadera);


      // =========================================================
      // PRODUCTOS
      // =========================================================

      // -------------------------
      // iPhone 15
      // -------------------------

      Producto iphone = new Producto();
      iphone.setNombre("iPhone 15");
      iphone.setDescription("Smartphone Apple con cámara de 48 MP");
      iphone.setPrecioBase(new BigDecimal("999999.00"));
      iphone.setActivo(true);
      iphone.setCategoria(celulares);
      iphone.setAtributos(List.of(
          almacenamiento,
          color
      ));

      iphone = productoRepository.save(iphone);


      // -------------------------
      // Notebook Lenovo
      // -------------------------

      Producto notebook = new Producto();
      notebook.setNombre("Notebook Lenovo IdeaPad");
      notebook.setDescription("Notebook de 14 pulgadas, 8GB RAM");
      notebook.setPrecioBase(new BigDecimal("750000.00"));
      notebook.setActivo(true);
      notebook.setCategoria(computacion);
      notebook.setAtributos(List.of(
          almacenamiento,
          color
      ));

      notebook = productoRepository.save(notebook);


      // -------------------------
      // Auriculares
      // -------------------------

      Producto auriculares = new Producto();
      auriculares.setNombre("Auriculares Bluetooth");
      auriculares.setDescription("Auriculares inalámbricos con cancelación de ruido");
      auriculares.setPrecioBase(new BigDecimal("45000.00"));
      auriculares.setActivo(true);
      auriculares.setCategoria(audio);
      auriculares.setAtributos(List.of(
          color
      ));

      auriculares = productoRepository.save(auriculares);


      // -------------------------
      // Campera térmica
      // -------------------------

      Producto campera = new Producto();
      campera.setNombre("Campera Térmica");
      campera.setDescription("Campera térmica para invierno");
      campera.setPrecioBase(new BigDecimal("120000.00"));
      campera.setActivo(true);
      campera.setCategoria(abrigos);
      campera.setAtributos(List.of(
          talle,
          color
      ));

      campera = productoRepository.save(campera);


      // -------------------------
      // Remera básica
      // -------------------------

      Producto remera = new Producto();
      remera.setNombre("Remera Básica");
      remera.setDescription("Remera de algodón de uso diario");
      remera.setPrecioBase(new BigDecimal("25000.00"));
      remera.setActivo(true);
      remera.setCategoria(remeras);
      remera.setAtributos(List.of(
          talle,
          color
      ));

      remera = productoRepository.save(remera);


      // -------------------------
      // Botella térmica
      // -------------------------

      Producto botella = new Producto();
      botella.setNombre("Botella Térmica");
      botella.setDescription("Botella de acero inoxidable");
      botella.setPrecioBase(new BigDecimal("30000.00"));
      botella.setActivo(true);
      botella.setCategoria(cocina);
      botella.setAtributos(List.of(
          capacidad,
          color,
          material
      ));

      botella = productoRepository.save(botella);


      // =========================================================
      // VARIANTES
      // =========================================================

      // -------------------------
      // iPhone 15
      // -------------------------

      VarianteProducto iphone128Negro = new VarianteProducto();
      iphone128Negro.setSku("IPH15-128-NG");
      iphone128Negro.setStock(10);
      iphone128Negro.setPrecioExtra(BigDecimal.ZERO);
      iphone128Negro.setProducto(iphone);
      iphone128Negro.setValoresAtributo(List.of(
          almacenamiento128,
          colorNegro
      ));

      VarianteProducto iphone256Azul = new VarianteProducto();
      iphone256Azul.setSku("IPH15-256-AZ");
      iphone256Azul.setStock(4);
      iphone256Azul.setPrecioExtra(new BigDecimal("150000.00"));
      iphone256Azul.setProducto(iphone);
      iphone256Azul.setValoresAtributo(List.of(
          almacenamiento256,
          colorAzul
      ));

      VarianteProducto iphone512Rojo = new VarianteProducto();
      iphone512Rojo.setSku("IPH15-512-RJ");
      iphone512Rojo.setStock(2);
      iphone512Rojo.setPrecioExtra(new BigDecimal("300000.00"));
      iphone512Rojo.setProducto(iphone);
      iphone512Rojo.setValoresAtributo(List.of(
          almacenamiento512,
          colorRojo
      ));

      varianteProductoRepository.save(iphone128Negro);
      varianteProductoRepository.save(iphone256Azul);
      varianteProductoRepository.save(iphone512Rojo);


      // -------------------------
      // Notebook Lenovo
      // -------------------------

      VarianteProducto notebook512Negra = new VarianteProducto();
      notebook512Negra.setSku("NB-LEN-512-NG");
      notebook512Negra.setStock(6);
      notebook512Negra.setPrecioExtra(BigDecimal.ZERO);
      notebook512Negra.setProducto(notebook);
      notebook512Negra.setValoresAtributo(List.of(
          almacenamiento512,
          colorNegro
      ));

      VarianteProducto notebook256Blanca = new VarianteProducto();
      notebook256Blanca.setSku("NB-LEN-256-BL");
      notebook256Blanca.setStock(3);
      notebook256Blanca.setPrecioExtra(new BigDecimal("-30000.00"));
      notebook256Blanca.setProducto(notebook);
      notebook256Blanca.setValoresAtributo(List.of(
          almacenamiento256,
          colorBlanco
      ));

      varianteProductoRepository.save(notebook512Negra);
      varianteProductoRepository.save(notebook256Blanca);


      // -------------------------
      // Campera térmica
      // -------------------------

      VarianteProducto camperaLNegra = new VarianteProducto();
      camperaLNegra.setSku("CMP-T-L-NG");
      camperaLNegra.setStock(15);
      camperaLNegra.setPrecioExtra(BigDecimal.ZERO);
      camperaLNegra.setProducto(campera);
      camperaLNegra.setValoresAtributo(List.of(
          talleL,
          colorNegro
      ));

      VarianteProducto camperaMAzul = new VarianteProducto();
      camperaMAzul.setSku("CMP-T-M-AZ");
      camperaMAzul.setStock(8);
      camperaMAzul.setPrecioExtra(BigDecimal.ZERO);
      camperaMAzul.setProducto(campera);
      camperaMAzul.setValoresAtributo(List.of(
          talleM,
          colorAzul
      ));

      VarianteProducto camperaXLNegra = new VarianteProducto();
      camperaXLNegra.setSku("CMP-T-XL-NG");
      camperaXLNegra.setStock(4);
      camperaXLNegra.setPrecioExtra(new BigDecimal("10000.00"));
      camperaXLNegra.setProducto(campera);
      camperaXLNegra.setValoresAtributo(List.of(
          talleXL,
          colorNegro
      ));

      varianteProductoRepository.save(camperaLNegra);
      varianteProductoRepository.save(camperaMAzul);
      varianteProductoRepository.save(camperaXLNegra);


      // -------------------------
      // Remera básica
      // -------------------------

      VarianteProducto remeraSBlanca = new VarianteProducto();
      remeraSBlanca.setSku("REM-B-S-BL");
      remeraSBlanca.setStock(20);
      remeraSBlanca.setPrecioExtra(BigDecimal.ZERO);
      remeraSBlanca.setProducto(remera);
      remeraSBlanca.setValoresAtributo(List.of(
          talleS,
          colorBlanco
      ));

      VarianteProducto remeraMNegra = new VarianteProducto();
      remeraMNegra.setSku("REM-B-M-NG");
      remeraMNegra.setStock(15);
      remeraMNegra.setPrecioExtra(BigDecimal.ZERO);
      remeraMNegra.setProducto(remera);
      remeraMNegra.setValoresAtributo(List.of(
          talleM,
          colorNegro
      ));

      varianteProductoRepository.save(remeraSBlanca);
      varianteProductoRepository.save(remeraMNegra);


      // -------------------------
      // Botella térmica
      // -------------------------

      VarianteProducto botella500Negra = new VarianteProducto();
      botella500Negra.setSku("BOT-500-NG");
      botella500Negra.setStock(12);
      botella500Negra.setPrecioExtra(BigDecimal.ZERO);
      botella500Negra.setProducto(botella);
      botella500Negra.setValoresAtributo(List.of(
          capacidad500,
          colorNegro,
          materialAcero
      ));

      VarianteProducto botella1LBlanca = new VarianteProducto();
      botella1LBlanca.setSku("BOT-1L-BL");
      botella1LBlanca.setStock(8);
      botella1LBlanca.setPrecioExtra(new BigDecimal("5000.00"));
      botella1LBlanca.setProducto(botella);
      botella1LBlanca.setValoresAtributo(List.of(
          capacidad1L,
          colorBlanco,
          materialAcero
      ));

      varianteProductoRepository.save(botella500Negra);
      varianteProductoRepository.save(botella1LBlanca);
    };
  }
}