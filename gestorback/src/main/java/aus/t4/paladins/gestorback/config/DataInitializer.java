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

      Departamento deportes = new Departamento();
      deportes.setNombre("Deportes");
      deportes = departamentoRepository.save(deportes);


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

      Categoria accesorios = new Categoria();
      accesorios.setNombre("Accesorios");
      accesorios.setDepartamento(electronica);

      celulares = categoriaRepository.save(celulares);
      computacion = categoriaRepository.save(computacion);
      audio = categoriaRepository.save(audio);
      accesorios = categoriaRepository.save(accesorios);


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

      Categoria calzado = new Categoria();
      calzado.setNombre("Calzado");
      calzado.setDepartamento(indumentaria);

      abrigos = categoriaRepository.save(abrigos);
      remeras = categoriaRepository.save(remeras);
      pantalones = categoriaRepository.save(pantalones);
      calzado = categoriaRepository.save(calzado);


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

      Categoria decoracion = new Categoria();
      decoracion.setNombre("Decoración");
      decoracion.setDepartamento(hogar);

      muebles = categoriaRepository.save(muebles);
      cocina = categoriaRepository.save(cocina);
      iluminacion = categoriaRepository.save(iluminacion);
      decoracion = categoriaRepository.save(decoracion);


      // -------------------------
      // Deportes
      // -------------------------

      Categoria entrenamiento = new Categoria();
      entrenamiento.setNombre("Entrenamiento");
      entrenamiento.setDepartamento(deportes);

      Categoria futbol = new Categoria();
      futbol.setNombre("Fútbol");
      futbol.setDepartamento(deportes);

      Categoria ciclismo = new Categoria();
      ciclismo.setNombre("Ciclismo");
      ciclismo.setDepartamento(deportes);

      entrenamiento = categoriaRepository.save(entrenamiento);
      futbol = categoriaRepository.save(futbol);
      ciclismo = categoriaRepository.save(ciclismo);


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

      Atributo memoria = new Atributo();
      memoria.setNombre("Memoria RAM");
      memoria = atributoRepository.save(memoria);

      Atributo talleCalzado = new Atributo();
      talleCalzado.setNombre("Talle calzado");
      talleCalzado = atributoRepository.save(talleCalzado);

      Atributo peso = new Atributo();
      peso.setNombre("Peso");
      peso = atributoRepository.save(peso);


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

      ValorAtributo colorVerde = new ValorAtributo();
      colorVerde.setValor("Verde");
      colorVerde.setAtributo(color);

      colorNegro = valorAtributoRepository.save(colorNegro);
      colorBlanco = valorAtributoRepository.save(colorBlanco);
      colorAzul = valorAtributoRepository.save(colorAzul);
      colorRojo = valorAtributoRepository.save(colorRojo);
      colorVerde = valorAtributoRepository.save(colorVerde);


      // -------------------------
      // Almacenamiento
      // -------------------------

      ValorAtributo almacenamiento64 = new ValorAtributo();
      almacenamiento64.setValor("64GB");
      almacenamiento64.setAtributo(almacenamiento);

      ValorAtributo almacenamiento128 = new ValorAtributo();
      almacenamiento128.setValor("128GB");
      almacenamiento128.setAtributo(almacenamiento);

      ValorAtributo almacenamiento256 = new ValorAtributo();
      almacenamiento256.setValor("256GB");
      almacenamiento256.setAtributo(almacenamiento);

      ValorAtributo almacenamiento512 = new ValorAtributo();
      almacenamiento512.setValor("512GB");
      almacenamiento512.setAtributo(almacenamiento);

      almacenamiento64 = valorAtributoRepository.save(almacenamiento64);
      almacenamiento128 = valorAtributoRepository.save(almacenamiento128);
      almacenamiento256 = valorAtributoRepository.save(almacenamiento256);
      almacenamiento512 = valorAtributoRepository.save(almacenamiento512);


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

      ValorAtributo materialPlastico = new ValorAtributo();
      materialPlastico.setValor("Plástico");
      materialPlastico.setAtributo(material);

      materialAlgodon = valorAtributoRepository.save(materialAlgodon);
      materialAcero = valorAtributoRepository.save(materialAcero);
      materialMadera = valorAtributoRepository.save(materialMadera);
      materialPlastico = valorAtributoRepository.save(materialPlastico);


      // -------------------------
      // Memoria RAM
      // -------------------------

      ValorAtributo ram4 = new ValorAtributo();
      ram4.setValor("4GB");
      ram4.setAtributo(memoria);

      ValorAtributo ram8 = new ValorAtributo();
      ram8.setValor("8GB");
      ram8.setAtributo(memoria);

      ValorAtributo ram16 = new ValorAtributo();
      ram16.setValor("16GB");
      ram16.setAtributo(memoria);

      ValorAtributo ram32 = new ValorAtributo();
      ram32.setValor("32GB");
      ram32.setAtributo(memoria);

      ram4 = valorAtributoRepository.save(ram4);
      ram8 = valorAtributoRepository.save(ram8);
      ram16 = valorAtributoRepository.save(ram16);
      ram32 = valorAtributoRepository.save(ram32);


      // -------------------------
      // Talle calzado
      // -------------------------

      ValorAtributo calzado38 = new ValorAtributo();
      calzado38.setValor("38");
      calzado38.setAtributo(talleCalzado);

      ValorAtributo calzado40 = new ValorAtributo();
      calzado40.setValor("40");
      calzado40.setAtributo(talleCalzado);

      ValorAtributo calzado42 = new ValorAtributo();
      calzado42.setValor("42");
      calzado42.setAtributo(talleCalzado);

      ValorAtributo calzado44 = new ValorAtributo();
      calzado44.setValor("44");
      calzado44.setAtributo(talleCalzado);

      calzado38 = valorAtributoRepository.save(calzado38);
      calzado40 = valorAtributoRepository.save(calzado40);
      calzado42 = valorAtributoRepository.save(calzado42);
      calzado44 = valorAtributoRepository.save(calzado44);


      // -------------------------
      // Peso
      // -------------------------

      ValorAtributo peso500g = new ValorAtributo();
      peso500g.setValor("500 g");
      peso500g.setAtributo(peso);

      ValorAtributo peso1kg = new ValorAtributo();
      peso1kg.setValor("1 kg");
      peso1kg.setAtributo(peso);

      ValorAtributo peso2kg = new ValorAtributo();
      peso2kg.setValor("2 kg");
      peso2kg.setAtributo(peso);

      ValorAtributo peso5kg = new ValorAtributo();
      peso5kg.setValor("5 kg");
      peso5kg.setAtributo(peso);

      peso500g = valorAtributoRepository.save(peso500g);
      peso1kg = valorAtributoRepository.save(peso1kg);
      peso2kg = valorAtributoRepository.save(peso2kg);
      peso5kg = valorAtributoRepository.save(peso5kg);


      // =========================================================
      // PRODUCTOS
      // =========================================================

      // -------------------------
      // iPhone 15 - 128GB
      // -------------------------

      Producto iphone128 = new Producto();
      iphone128.setNombre("iPhone 15");
      iphone128.setDescription("Smartphone Apple con cámara de 48 MP");
      iphone128.setPrecioBase(new BigDecimal("999999.00"));
      iphone128.setActivo(true);
      iphone128.setCategoria(celulares);
      iphone128.setAtributos(List.of(almacenamiento, color));
      iphone128 = productoRepository.save(iphone128);


      // -------------------------
      // iPhone 15 - 256GB
      // -------------------------

      Producto iphone256 = new Producto();
      iphone256.setNombre("iPhone 15");
      iphone256.setDescription("Smartphone Apple con cámara de 48 MP");
      iphone256.setPrecioBase(new BigDecimal("999999.00"));
      iphone256.setActivo(true);
      iphone256.setCategoria(celulares);
      iphone256.setAtributos(List.of(almacenamiento, color));
      iphone256 = productoRepository.save(iphone256);


      // -------------------------
      // iPhone 15 - 512GB
      // -------------------------

      Producto iphone512 = new Producto();
      iphone512.setNombre("iPhone 15");
      iphone512.setDescription("Smartphone Apple con cámara de 48 MP");
      iphone512.setPrecioBase(new BigDecimal("999999.00"));
      iphone512.setActivo(true);
      iphone512.setCategoria(celulares);
      iphone512.setAtributos(List.of(almacenamiento, color));
      iphone512 = productoRepository.save(iphone512);


      // -------------------------
      // Samsung Galaxy A55
      // -------------------------

      Producto samsung = new Producto();
      samsung.setNombre("Samsung Galaxy A55");
      samsung.setDescription("Smartphone Samsung de gama media");
      samsung.setPrecioBase(new BigDecimal("699999.00"));
      samsung.setActivo(true);
      samsung.setCategoria(celulares);
      samsung.setAtributos(List.of(almacenamiento, color));
      samsung = productoRepository.save(samsung);


      // -------------------------
      // Notebook Lenovo - 512GB
      // -------------------------

      Producto notebook512 = new Producto();
      notebook512.setNombre("Notebook Lenovo IdeaPad");
      notebook512.setDescription("Notebook de 14 pulgadas para uso general");
      notebook512.setPrecioBase(new BigDecimal("750000.00"));
      notebook512.setActivo(true);
      notebook512.setCategoria(computacion);
      notebook512.setAtributos(List.of(almacenamiento, color, memoria));
      notebook512 = productoRepository.save(notebook512);


      // -------------------------
      // Notebook Lenovo - 256GB
      // -------------------------

      Producto notebook256 = new Producto();
      notebook256.setNombre("Notebook Lenovo IdeaPad");
      notebook256.setDescription("Notebook de 14 pulgadas para uso general");
      notebook256.setPrecioBase(new BigDecimal("750000.00"));
      notebook256.setActivo(true);
      notebook256.setCategoria(computacion);
      notebook256.setAtributos(List.of(almacenamiento, color, memoria));
      notebook256 = productoRepository.save(notebook256);


      // -------------------------
      // Auriculares Bluetooth
      // -------------------------

      Producto auriculares = new Producto();
      auriculares.setNombre("Auriculares Bluetooth");
      auriculares.setDescription("Auriculares inalámbricos con cancelación de ruido");
      auriculares.setPrecioBase(new BigDecimal("45000.00"));
      auriculares.setActivo(true);
      auriculares.setCategoria(audio);
      auriculares.setAtributos(List.of(color));
      auriculares = productoRepository.save(auriculares);


      // -------------------------
      // Parlante portátil
      // -------------------------

      Producto parlante = new Producto();
      parlante.setNombre("Parlante Portátil");
      parlante.setDescription("Parlante Bluetooth portátil");
      parlante.setPrecioBase(new BigDecimal("80000.00"));
      parlante.setActivo(true);
      parlante.setCategoria(audio);
      parlante.setAtributos(List.of(color, capacidad));
      parlante = productoRepository.save(parlante);


      // -------------------------
      // Mouse inalámbrico
      // -------------------------

      Producto mouse = new Producto();
      mouse.setNombre("Mouse Inalámbrico");
      mouse.setDescription("Mouse inalámbrico ergonómico");
      mouse.setPrecioBase(new BigDecimal("25000.00"));
      mouse.setActivo(true);
      mouse.setCategoria(accesorios);
      mouse.setAtributos(List.of(color));
      mouse = productoRepository.save(mouse);


      // -------------------------
      // Campera térmica - L
      // -------------------------

      Producto camperaL = new Producto();
      camperaL.setNombre("Campera Térmica");
      camperaL.setDescription("Campera térmica para invierno");
      camperaL.setPrecioBase(new BigDecimal("120000.00"));
      camperaL.setActivo(true);
      camperaL.setCategoria(abrigos);
      camperaL.setAtributos(List.of(talle, color));
      camperaL = productoRepository.save(camperaL);


      // -------------------------
      // Campera térmica - M
      // -------------------------

      Producto camperaM = new Producto();
      camperaM.setNombre("Campera Térmica");
      camperaM.setDescription("Campera térmica para invierno");
      camperaM.setPrecioBase(new BigDecimal("120000.00"));
      camperaM.setActivo(true);
      camperaM.setCategoria(abrigos);
      camperaM.setAtributos(List.of(talle, color));
      camperaM = productoRepository.save(camperaM);


      // -------------------------
      // Remera básica - S
      // -------------------------

      Producto remeraS = new Producto();
      remeraS.setNombre("Remera Básica");
      remeraS.setDescription("Remera de algodón de uso diario");
      remeraS.setPrecioBase(new BigDecimal("25000.00"));
      remeraS.setActivo(true);
      remeraS.setCategoria(remeras);
      remeraS.setAtributos(List.of(talle, color, material));
      remeraS = productoRepository.save(remeraS);


      // -------------------------
      // Remera básica - M
      // -------------------------

      Producto remeraM = new Producto();
      remeraM.setNombre("Remera Básica");
      remeraM.setDescription("Remera de algodón de uso diario");
      remeraM.setPrecioBase(new BigDecimal("25000.00"));
      remeraM.setActivo(true);
      remeraM.setCategoria(remeras);
      remeraM.setAtributos(List.of(talle, color, material));
      remeraM = productoRepository.save(remeraM);


      // -------------------------
      // Pantalón deportivo
      // -------------------------

      Producto pantalon = new Producto();
      pantalon.setNombre("Pantalón Deportivo");
      pantalon.setDescription("Pantalón deportivo de algodón");
      pantalon.setPrecioBase(new BigDecimal("45000.00"));
      pantalon.setActivo(true);
      pantalon.setCategoria(pantalones);
      pantalon.setAtributos(List.of(talle, color));
      pantalon = productoRepository.save(pantalon);


      // -------------------------
      // Zapatillas deportivas
      // -------------------------

      Producto zapatillas = new Producto();
      zapatillas.setNombre("Zapatillas Deportivas");
      zapatillas.setDescription("Zapatillas para entrenamiento");
      zapatillas.setPrecioBase(new BigDecimal("95000.00"));
      zapatillas.setActivo(true);
      zapatillas.setCategoria(calzado);
      zapatillas.setAtributos(List.of(talleCalzado, color));
      zapatillas = productoRepository.save(zapatillas);


      // -------------------------
      // Botella térmica
      // -------------------------

      Producto botella = new Producto();
      botella.setNombre("Botella Térmica");
      botella.setDescription("Botella de acero inoxidable");
      botella.setPrecioBase(new BigDecimal("30000.00"));
      botella.setActivo(true);
      botella.setCategoria(cocina);
      botella.setAtributos(List.of(capacidad, color, material));
      botella = productoRepository.save(botella);


      // -------------------------
      // Taza térmica
      // -------------------------

      Producto taza = new Producto();
      taza.setNombre("Taza Térmica");
      taza.setDescription("Taza térmica para bebidas calientes");
      taza.setPrecioBase(new BigDecimal("18000.00"));
      taza.setActivo(true);
      taza.setCategoria(cocina);
      taza.setAtributos(List.of(capacidad, color, material));
      taza = productoRepository.save(taza);


      // -------------------------
      // Lámpara de escritorio
      // -------------------------

      Producto lampara = new Producto();
      lampara.setNombre("Lámpara de Escritorio");
      lampara.setDescription("Lámpara LED de escritorio");
      lampara.setPrecioBase(new BigDecimal("35000.00"));
      lampara.setActivo(true);
      lampara.setCategoria(iluminacion);
      lampara.setAtributos(List.of(color, material));
      lampara = productoRepository.save(lampara);


      // -------------------------
      // Silla de escritorio
      // -------------------------

      Producto silla = new Producto();
      silla.setNombre("Silla de Escritorio");
      silla.setDescription("Silla ergonómica para escritorio");
      silla.setPrecioBase(new BigDecimal("180000.00"));
      silla.setActivo(true);
      silla.setCategoria(muebles);
      silla.setAtributos(List.of(color, material));
      silla = productoRepository.save(silla);


      // -------------------------
      // Pelota de fútbol
      // -------------------------

      Producto pelota = new Producto();
      pelota.setNombre("Pelota de Fútbol");
      pelota.setDescription("Pelota de fútbol para entrenamiento");
      pelota.setPrecioBase(new BigDecimal("35000.00"));
      pelota.setActivo(true);
      pelota.setCategoria(futbol);
      pelota.setAtributos(List.of(color, material, peso));
      pelota = productoRepository.save(pelota);


      // -------------------------
      // Mancuernas
      // -------------------------

      Producto mancuernas = new Producto();
      mancuernas.setNombre("Mancuernas");
      mancuernas.setDescription("Mancuernas para entrenamiento");
      mancuernas.setPrecioBase(new BigDecimal("50000.00"));
      mancuernas.setActivo(true);
      mancuernas.setCategoria(entrenamiento);
      mancuernas.setAtributos(List.of(peso, material, color));
      mancuernas = productoRepository.save(mancuernas);


      // -------------------------
      // Bicicleta urbana
      // -------------------------

      Producto bicicleta = new Producto();
      bicicleta.setNombre("Bicicleta Urbana");
      bicicleta.setDescription("Bicicleta para uso urbano");
      bicicleta.setPrecioBase(new BigDecimal("450000.00"));
      bicicleta.setActivo(true);
      bicicleta.setCategoria(ciclismo);
      bicicleta.setAtributos(List.of(color, material));
      bicicleta = productoRepository.save(bicicleta);


      // =========================================================
      // VARIANTES
      // =========================================================

      // -------------------------
      // iPhone 15 - 128GB
      // -------------------------

      VarianteProducto iphone128Negro = new VarianteProducto();
      iphone128Negro.setSku("IPH15-128-NG");
      iphone128Negro.setStock(10);
      iphone128Negro.setPrecioExtra(BigDecimal.ZERO);
      iphone128Negro.setProducto(iphone128);
      iphone128Negro.setValoresAtributo(List.of(
          almacenamiento128,
          colorNegro
      ));
      varianteProductoRepository.save(iphone128Negro);


      // -------------------------
      // iPhone 15 - 256GB
      // -------------------------

      VarianteProducto iphone256Azul = new VarianteProducto();
      iphone256Azul.setSku("IPH15-256-AZ");
      iphone256Azul.setStock(4);
      iphone256Azul.setPrecioExtra(new BigDecimal("150000.00"));
      iphone256Azul.setProducto(iphone256);
      iphone256Azul.setValoresAtributo(List.of(
          almacenamiento256,
          colorAzul
      ));
      varianteProductoRepository.save(iphone256Azul);


      // -------------------------
      // iPhone 15 - 512GB
      // -------------------------

      VarianteProducto iphone512Rojo = new VarianteProducto();
      iphone512Rojo.setSku("IPH15-512-RJ");
      iphone512Rojo.setStock(2);
      iphone512Rojo.setPrecioExtra(new BigDecimal("300000.00"));
      iphone512Rojo.setProducto(iphone512);
      iphone512Rojo.setValoresAtributo(List.of(
          almacenamiento512,
          colorRojo
      ));
      varianteProductoRepository.save(iphone512Rojo);


      // -------------------------
      // Samsung Galaxy A55
      // -------------------------

      VarianteProducto samsungNegro = new VarianteProducto();
      samsungNegro.setSku("SAM-A55-128-NG");
      samsungNegro.setStock(12);
      samsungNegro.setPrecioExtra(BigDecimal.ZERO);
      samsungNegro.setProducto(samsung);
      samsungNegro.setValoresAtributo(List.of(
          almacenamiento128,
          colorNegro
      ));
      varianteProductoRepository.save(samsungNegro);


      // -------------------------
      // Notebook Lenovo - 512GB
      // -------------------------

      VarianteProducto notebook512Negra = new VarianteProducto();
      notebook512Negra.setSku("NB-LEN-512-NG");
      notebook512Negra.setStock(6);
      notebook512Negra.setPrecioExtra(BigDecimal.ZERO);
      notebook512Negra.setProducto(notebook512);
      notebook512Negra.setValoresAtributo(List.of(
          almacenamiento512,
          colorNegro,
          ram16
      ));
      varianteProductoRepository.save(notebook512Negra);


      // -------------------------
      // Notebook Lenovo - 256GB
      // -------------------------

      VarianteProducto notebook256Blanca = new VarianteProducto();
      notebook256Blanca.setSku("NB-LEN-256-BL");
      notebook256Blanca.setStock(3);
      notebook256Blanca.setPrecioExtra(new BigDecimal("-30000.00"));
      notebook256Blanca.setProducto(notebook256);
      notebook256Blanca.setValoresAtributo(List.of(
          almacenamiento256,
          colorBlanco,
          ram8
      ));
      varianteProductoRepository.save(notebook256Blanca);


      // -------------------------
      // Auriculares Bluetooth
      // -------------------------

      VarianteProducto auricularesNegros = new VarianteProducto();
      auricularesNegros.setSku("AUR-BT-NG");
      auricularesNegros.setStock(15);
      auricularesNegros.setPrecioExtra(BigDecimal.ZERO);
      auricularesNegros.setProducto(auriculares);
      auricularesNegros.setValoresAtributo(List.of(
          colorNegro
      ));
      varianteProductoRepository.save(auricularesNegros);


      // -------------------------
      // Parlante portátil
      // -------------------------

      VarianteProducto parlanteAzul = new VarianteProducto();
      parlanteAzul.setSku("PAR-BT-AZ");
      parlanteAzul.setStock(8);
      parlanteAzul.setPrecioExtra(BigDecimal.ZERO);
      parlanteAzul.setProducto(parlante);
      parlanteAzul.setValoresAtributo(List.of(
          colorAzul,
          capacidad1L
      ));
      varianteProductoRepository.save(parlanteAzul);


      // -------------------------
      // Mouse inalámbrico
      // -------------------------

      VarianteProducto mouseNegro = new VarianteProducto();
      mouseNegro.setSku("MOU-WL-NG");
      mouseNegro.setStock(20);
      mouseNegro.setPrecioExtra(BigDecimal.ZERO);
      mouseNegro.setProducto(mouse);
      mouseNegro.setValoresAtributo(List.of(
          colorNegro
      ));
      varianteProductoRepository.save(mouseNegro);


      // -------------------------
      // Campera térmica - L
      // -------------------------

      VarianteProducto camperaLNegra = new VarianteProducto();
      camperaLNegra.setSku("CMP-T-L-NG");
      camperaLNegra.setStock(15);
      camperaLNegra.setPrecioExtra(BigDecimal.ZERO);
      camperaLNegra.setProducto(camperaL);
      camperaLNegra.setValoresAtributo(List.of(
          talleL,
          colorNegro
      ));
      varianteProductoRepository.save(camperaLNegra);


      // -------------------------
      // Campera térmica - M
      // -------------------------

      VarianteProducto camperaMAzul = new VarianteProducto();
      camperaMAzul.setSku("CMP-T-M-AZ");
      camperaMAzul.setStock(8);
      camperaMAzul.setPrecioExtra(BigDecimal.ZERO);
      camperaMAzul.setProducto(camperaM);
      camperaMAzul.setValoresAtributo(List.of(
          talleM,
          colorAzul
      ));
      varianteProductoRepository.save(camperaMAzul);


      // -------------------------
      // Remera básica - S
      // -------------------------

      VarianteProducto remeraSBlanca = new VarianteProducto();
      remeraSBlanca.setSku("REM-B-S-BL");
      remeraSBlanca.setStock(20);
      remeraSBlanca.setPrecioExtra(BigDecimal.ZERO);
      remeraSBlanca.setProducto(remeraS);
      remeraSBlanca.setValoresAtributo(List.of(
          talleS,
          colorBlanco,
          materialAlgodon
      ));
      varianteProductoRepository.save(remeraSBlanca);


      // -------------------------
      // Remera básica - M
      // -------------------------

      VarianteProducto remeraMNegra = new VarianteProducto();
      remeraMNegra.setSku("REM-B-M-NG");
      remeraMNegra.setStock(15);
      remeraMNegra.setPrecioExtra(BigDecimal.ZERO);
      remeraMNegra.setProducto(remeraM);
      remeraMNegra.setValoresAtributo(List.of(
          talleM,
          colorNegro,
          materialAlgodon
      ));
      varianteProductoRepository.save(remeraMNegra);


      // -------------------------
      // Pantalón deportivo
      // -------------------------

      VarianteProducto pantalonNegro = new VarianteProducto();
      pantalonNegro.setSku("PAN-DEP-L-NG");
      pantalonNegro.setStock(10);
      pantalonNegro.setPrecioExtra(BigDecimal.ZERO);
      pantalonNegro.setProducto(pantalon);
      pantalonNegro.setValoresAtributo(List.of(
          talleL,
          colorNegro
      ));
      varianteProductoRepository.save(pantalonNegro);


      // -------------------------
      // Zapatillas deportivas
      // -------------------------

      VarianteProducto zapatillas42 = new VarianteProducto();
      zapatillas42.setSku("ZAP-DEP-42-NG");
      zapatillas42.setStock(7);
      zapatillas42.setPrecioExtra(BigDecimal.ZERO);
      zapatillas42.setProducto(zapatillas);
      zapatillas42.setValoresAtributo(List.of(
          calzado42,
          colorNegro
      ));
      varianteProductoRepository.save(zapatillas42);


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
      varianteProductoRepository.save(botella500Negra);


      // -------------------------
      // Taza térmica
      // -------------------------

      VarianteProducto taza500Blanca = new VarianteProducto();
      taza500Blanca.setSku("TAZ-500-BL");
      taza500Blanca.setStock(18);
      taza500Blanca.setPrecioExtra(BigDecimal.ZERO);
      taza500Blanca.setProducto(taza);
      taza500Blanca.setValoresAtributo(List.of(
          capacidad500,
          colorBlanco,
          materialAcero
      ));
      varianteProductoRepository.save(taza500Blanca);


      // -------------------------
      // Lámpara de escritorio
      // -------------------------

      VarianteProducto lamparaNegra = new VarianteProducto();
      lamparaNegra.setSku("LAM-ESC-NG");
      lamparaNegra.setStock(9);
      lamparaNegra.setPrecioExtra(BigDecimal.ZERO);
      lamparaNegra.setProducto(lampara);
      lamparaNegra.setValoresAtributo(List.of(
          colorNegro,
          materialPlastico
      ));
      varianteProductoRepository.save(lamparaNegra);


      // -------------------------
      // Silla de escritorio
      // -------------------------

      VarianteProducto sillaNegra = new VarianteProducto();
      sillaNegra.setSku("SIL-ESC-NG");
      sillaNegra.setStock(5);
      sillaNegra.setPrecioExtra(BigDecimal.ZERO);
      sillaNegra.setProducto(silla);
      sillaNegra.setValoresAtributo(List.of(
          colorNegro,
          materialPlastico
      ));
      varianteProductoRepository.save(sillaNegra);


      // -------------------------
      // Pelota de fútbol
      // -------------------------

      VarianteProducto pelotaBlanca = new VarianteProducto();
      pelotaBlanca.setSku("PEL-FUT-BL");
      pelotaBlanca.setStock(14);
      pelotaBlanca.setPrecioExtra(BigDecimal.ZERO);
      pelotaBlanca.setProducto(pelota);
      pelotaBlanca.setValoresAtributo(List.of(
          colorBlanco,
          materialPlastico,
          peso500g
      ));
      varianteProductoRepository.save(pelotaBlanca);


      // -------------------------
      // Mancuernas
      // -------------------------

      VarianteProducto mancuernas2kg = new VarianteProducto();
      mancuernas2kg.setSku("MAN-2KG-NG");
      mancuernas2kg.setStock(10);
      mancuernas2kg.setPrecioExtra(BigDecimal.ZERO);
      mancuernas2kg.setProducto(mancuernas);
      mancuernas2kg.setValoresAtributo(List.of(
          peso2kg,
          materialAcero,
          colorNegro
      ));
      varianteProductoRepository.save(mancuernas2kg);


      // -------------------------
      // Bicicleta urbana
      // -------------------------

      VarianteProducto bicicletaNegra = new VarianteProducto();
      bicicletaNegra.setSku("BIC-URB-NG");
      bicicletaNegra.setStock(4);
      bicicletaNegra.setPrecioExtra(BigDecimal.ZERO);
      bicicletaNegra.setProducto(bicicleta);
      bicicletaNegra.setValoresAtributo(List.of(
          colorNegro,
          materialAcero
      ));
      varianteProductoRepository.save(bicicletaNegra);
    };
  }
}