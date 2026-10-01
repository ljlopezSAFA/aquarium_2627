-- =====================================================================
--  ACUARIOFILIA · Modelo de datos PostgreSQL (versión didáctica)
--  Enciclopedia para aficionados + gestión de acuarios de usuario
--  API REST con Spring Boot (Spring Data JPA) + aplicación móvil
--  Compatible con PostgreSQL 12 o superior
-- =====================================================================
--  Convenciones:
--   · Clave primaria:  id SERIAL          (catálogos: id SMALLSERIAL)
--   · Clave foránea:   id_<tabla>         (id_usuario, id_especie, id_dieta...)
--   · Opciones:        tabla cat_xxx + columna id_xxx SMALLINT
--   · Tipos en Java:   SERIAL -> Integer · SMALLINT -> Short · NUMERIC -> BigDecimal
--                      DATE -> LocalDate · TIMESTAMP -> LocalDateTime · TIME -> LocalTime
--   · creado_en / actualizado_en -> @CreationTimestamp / @UpdateTimestamp en la entidad
--   · Imágenes: se guarda la URL, no el archivo
--   · La lógica (alertas, cálculos, avisos) se programa en los servicios Java
-- =====================================================================
--  Uso: crear la base de datos (CREATE DATABASE acuariofilia;),
--       conectarse a ella y ejecutar este script completo.
-- =====================================================================

-- Borra las tablas si ya existían, para poder relanzar el script
DROP TABLE IF EXISTS
    usuario_especie_guardada,
    glosario_termino,
    articulo_especie,
    articulo,
    acuario_foto,
    diario_entrada,
    episodio_salud,
    registro_mantenimiento,
    tarea_programada,
    medicion_agua,
    acuario_equipamiento,
    acuario_habitante,
    acuario,
    equipamiento,
    categoria_equipamiento,
    especie_enfermedad,
    enfermedad_medicamento,
    medicamento,
    enfermedad_sintoma,
    sintoma,
    enfermedad,
    especie_alimento,
    alimento,
    marca,
    compatibilidad,
    especie_biotopo,
    especie_imagen,
    especie_nombre_alternativo,
    especie_invertebrado,
    especie_planta,
    especie_pez,
    especie,
    biotopo,
    familia,
    dispositivo_push,
    token_refresco,
    usuario,
    cat_lista_guardada,
    cat_categoria_articulo,
    cat_resultado_episodio,
    cat_tipo_mantenimiento,
    cat_metodo_medicion,
    cat_motivo_baja,
    cat_estado_acuario,
    cat_estilo_acuario,
    cat_zona_sintoma,
    cat_gravedad,
    cat_agente_enfermedad,
    cat_tipo_alimento,
    cat_motivo_compatibilidad,
    cat_estado_compatibilidad,
    cat_grupo_invertebrado,
    cat_tasa_crecimiento,
    cat_nivel_luz,
    cat_posicion_planta,
    cat_tipo_plantado,
    cat_estado_conservacion,
    cat_tipo_reproduccion,
    cat_nivel_nado,
    cat_temperamento,
    cat_dieta,
    cat_dificultad,
    cat_tipo_agua,
    cat_continente,
    cat_tipo_organismo,
    cat_plataforma,
    cat_unidad_volumen,
    cat_unidad_temperatura,
    cat_rol_usuario
CASCADE;

-- =====================================================================
-- 0. CATÁLOGOS DE OPCIONES (sustituyen a los choices de Django)
--    Todos tienen la misma estructura:
--      id      SMALLSERIAL -> el valor que se guarda en las tablas (SMALLINT)
--      codigo  constante estable para programar (coincide con el enum Java)
--      nombre  texto que muestra la app (se puede cambiar libremente)
--      orden   orden en desplegables; activo = FALSE oculta la opción sin borrarla
-- =====================================================================
CREATE TABLE cat_rol_usuario (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_rol_usuario (codigo, nombre, orden) VALUES
    ('USUARIO', 'Usuario', 1),
    ('EDITOR', 'Editor', 2),
    ('ADMIN', 'Administrador', 3);
CREATE TABLE cat_unidad_temperatura (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_unidad_temperatura (codigo, nombre, orden) VALUES
    ('C', 'Grados Celsius (ºC)', 1),
    ('F', 'Grados Fahrenheit (ºF)', 2);
CREATE TABLE cat_unidad_volumen (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_unidad_volumen (codigo, nombre, orden) VALUES
    ('L', 'Litros', 1),
    ('GAL', 'Galones', 2);
CREATE TABLE cat_plataforma (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_plataforma (codigo, nombre, orden) VALUES
    ('ANDROID', 'Android', 1),
    ('IOS', 'iOS', 2),
    ('WEB', 'Web', 3);
CREATE TABLE cat_tipo_organismo (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tipo_organismo (codigo, nombre, orden) VALUES
    ('PEZ', 'Pez 🐟', 1),
    ('PLANTA', 'Planta 🌱', 2),
    ('INVERTEBRADO', 'Invertebrado 🦐', 3);
CREATE TABLE cat_continente (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_continente (codigo, nombre, orden) VALUES
    ('AMERICA_SUR', 'Sudamérica', 1),
    ('AMERICA_CENTRAL', 'Centroamérica', 2),
    ('AMERICA_NORTE', 'Norteamérica', 3),
    ('AFRICA', 'África', 4),
    ('ASIA', 'Asia', 5),
    ('OCEANIA', 'Oceanía', 6),
    ('EUROPA', 'Europa', 7),
    ('CULTIVO', 'Variedad de cultivo', 8);
CREATE TABLE cat_tipo_agua (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tipo_agua (codigo, nombre, orden) VALUES
    ('DULCE', 'Agua dulce', 1),
    ('SALOBRE', 'Agua salobre', 2),
    ('MARINA', 'Agua marina', 3);
CREATE TABLE cat_dificultad (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_dificultad (codigo, nombre, orden) VALUES
    ('FACIL', 'Fácil', 1),
    ('MEDIA', 'Media', 2),
    ('DIFICIL', 'Difícil', 3),
    ('EXPERTO', 'Experto', 4);
CREATE TABLE cat_dieta (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_dieta (codigo, nombre, orden) VALUES
    ('HERBIVORO', 'Herbívoro 🌿', 1),
    ('CARNIVORO', 'Carnívoro 🥩', 2),
    ('OMNIVORO', 'Omnívoro 🌿🥩', 3),
    ('DETRITIVORO', 'Detritívoro 🍂', 4),
    ('PLANCTIVORO', 'Planctívoro', 5);
CREATE TABLE cat_temperamento (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_temperamento (codigo, nombre, orden) VALUES
    ('PACIFICO', 'Pacífico', 1),
    ('SEMI_AGRESIVO', 'Semi-agresivo', 2),
    ('AGRESIVO', 'Agresivo', 3),
    ('TERRITORIAL', 'Territorial', 4);
CREATE TABLE cat_nivel_nado (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_nivel_nado (codigo, nombre, orden) VALUES
    ('SUPERFICIE', 'Superficie', 1),
    ('MEDIO', 'Medio', 2),
    ('FONDO', 'Fondo', 3),
    ('TODOS', 'Todos los niveles', 4);
CREATE TABLE cat_tipo_reproduccion (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tipo_reproduccion (codigo, nombre, orden) VALUES
    ('OVIPARO_DISPERSOR', 'Ovíparo dispersor', 1),
    ('OVIPARO_ADHERENTE', 'Ovíparo de huevos adherentes', 2),
    ('NIDO_BURBUJAS', 'Constructor de nido de burbujas', 3),
    ('INCUBADOR_BUCAL', 'Incubador bucal', 4),
    ('CUEVA', 'Desovador en cueva', 5),
    ('VIVIPARO', 'Vivíparo', 6),
    ('KILLI_ANUAL', 'Killi anual', 7),
    ('NO_REPRODUCIBLE_CAUTIVIDAD', 'No se reproduce en cautividad', 8);
CREATE TABLE cat_estado_conservacion (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_estado_conservacion (codigo, nombre, orden) VALUES
    ('NE', 'No evaluado', 1),
    ('DD', 'Datos insuficientes', 2),
    ('LC', 'Preocupación menor', 3),
    ('NT', 'Casi amenazado', 4),
    ('VU', 'Vulnerable', 5),
    ('EN', 'En peligro', 6),
    ('CR', 'En peligro crítico', 7),
    ('EW', 'Extinto en estado silvestre', 8),
    ('EX', 'Extinto', 9);
CREATE TABLE cat_tipo_plantado (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tipo_plantado (codigo, nombre, orden) VALUES
    ('TAPIZANTE', 'Tapizante', 1),
    ('ENTERRADA', 'Enterrada en sustrato', 2),
    ('RIZOMA', 'Rizoma (anclada a decoración)', 3),
    ('FLOTANTE', 'Flotante', 4),
    ('MUSGO', 'Musgo', 5),
    ('BULBO', 'Bulbo / tubérculo', 6),
    ('EMERGIDA', 'Emergida / paludario', 7);
CREATE TABLE cat_posicion_planta (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_posicion_planta (codigo, nombre, orden) VALUES
    ('DELANTERA', 'Delantera', 1),
    ('MEDIA', 'Zona media', 2),
    ('TRASERA', 'Trasera (fondo)', 3),
    ('SUPERFICIE', 'Superficie', 4);
CREATE TABLE cat_nivel_luz (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_nivel_luz (codigo, nombre, orden) VALUES
    ('BAJA', 'Baja', 1),
    ('MEDIA', 'Media', 2),
    ('ALTA', 'Alta', 3);
CREATE TABLE cat_tasa_crecimiento (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tasa_crecimiento (codigo, nombre, orden) VALUES
    ('MUY_LENTA', 'Muy lenta', 1),
    ('LENTA', 'Lenta', 2),
    ('MEDIA', 'Media', 3),
    ('RAPIDA', 'Rápida', 4);
CREATE TABLE cat_grupo_invertebrado (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_grupo_invertebrado (codigo, nombre, orden) VALUES
    ('GAMBA', 'Gamba', 1),
    ('CANGREJO', 'Cangrejo', 2),
    ('CANGREJO_RIO', 'Cangrejo de río', 3),
    ('CARACOL', 'Caracol', 4),
    ('BIVALVO', 'Bivalvo', 5),
    ('OTRO', 'Otro', 6);
CREATE TABLE cat_estado_compatibilidad (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_estado_compatibilidad (codigo, nombre, orden) VALUES
    ('IDEAL', 'Compañeros ideales', 1),
    ('PRECAUCION', 'Precaución', 2),
    ('INCOMPATIBLE', 'Incompatibles', 3);
CREATE TABLE cat_motivo_compatibilidad (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_motivo_compatibilidad (codigo, nombre, orden) VALUES
    ('DEPREDACION', 'Depredación', 1),
    ('AGRESIVIDAD', 'Agresividad', 2),
    ('PARAMETROS_AGUA', 'Parámetros de agua distintos', 3),
    ('TAMANO', 'Diferencia de tamaño', 4),
    ('COMPETENCIA_ALIMENTO', 'Competencia por el alimento', 5),
    ('MORDISQUEO_ALETAS', 'Mordisquea aletas', 6),
    ('COME_PLANTAS', 'Come plantas', 7),
    ('OTRO', 'Otro', 8);
CREATE TABLE cat_tipo_alimento (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tipo_alimento (codigo, nombre, orden) VALUES
    ('ESCAMAS', 'Escamas', 1),
    ('GRANULADO', 'Granulado', 2),
    ('PASTILLA', 'Pastillas de fondo', 3),
    ('CONGELADO', 'Congelado', 4),
    ('VIVO', 'Vivo', 5),
    ('LIOFILIZADO', 'Liofilizado', 6),
    ('VEGETAL_FRESCO', 'Vegetal fresco', 7),
    ('GEL', 'Gel', 8),
    ('OTRO', 'Otro', 9);
CREATE TABLE cat_agente_enfermedad (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_agente_enfermedad (codigo, nombre, orden) VALUES
    ('BACTERIA', 'Bacteria', 1),
    ('HONGO', 'Hongo', 2),
    ('PARASITO_EXTERNO', 'Parásito externo', 3),
    ('PARASITO_INTERNO', 'Parásito interno', 4),
    ('VIRUS', 'Virus', 5),
    ('AMBIENTAL', 'Ambiental', 6),
    ('NUTRICIONAL', 'Nutricional', 7),
    ('DESCONOCIDO', 'Desconocido', 8);
CREATE TABLE cat_gravedad (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_gravedad (codigo, nombre, orden) VALUES
    ('LEVE', 'Leve', 1),
    ('MODERADA', 'Moderada', 2),
    ('GRAVE', 'Grave', 3);
CREATE TABLE cat_zona_sintoma (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_zona_sintoma (codigo, nombre, orden) VALUES
    ('PIEL', 'Piel', 1),
    ('ALETAS', 'Aletas', 2),
    ('BRANQUIAS', 'Branquias', 3),
    ('OJOS', 'Ojos', 4),
    ('VIENTRE', 'Vientre', 5),
    ('BOCA', 'Boca', 6),
    ('COMPORTAMIENTO', 'Comportamiento', 7),
    ('GENERAL', 'General', 8);
CREATE TABLE cat_estilo_acuario (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_estilo_acuario (codigo, nombre, orden) VALUES
    ('COMUNITARIO', 'Comunitario', 1),
    ('BIOTOPO', 'Biotopo', 2),
    ('HOLANDES', 'Holandés', 3),
    ('NATURAL', 'Natural (estilo Amano)', 4),
    ('IWAGUMI', 'Iwagumi', 5),
    ('JUNGLA', 'Jungla', 6),
    ('GAMBARIO', 'Gambario', 7),
    ('NANO', 'Nano', 8),
    ('CRIA', 'Cría', 9),
    ('CUARENTENA', 'Cuarentena', 10),
    ('PALUDARIO', 'Paludario', 11),
    ('OTRO', 'Otro', 12);
CREATE TABLE cat_estado_acuario (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_estado_acuario (codigo, nombre, orden) VALUES
    ('CICLANDO', 'Ciclando', 1),
    ('ACTIVO', 'Activo', 2),
    ('INACTIVO', 'Inactivo', 3),
    ('DESMONTADO', 'Desmontado', 4);
CREATE TABLE cat_motivo_baja (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_motivo_baja (codigo, nombre, orden) VALUES
    ('MUERTE', 'Muerte', 1),
    ('TRASLADO', 'Traslado a otro acuario', 2),
    ('VENTA', 'Venta', 3),
    ('REGALO', 'Regalo', 4),
    ('RETIRADA', 'Retirada', 5),
    ('OTRO', 'Otro', 6);
CREATE TABLE cat_metodo_medicion (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_metodo_medicion (codigo, nombre, orden) VALUES
    ('TIRAS', 'Tiras reactivas', 1),
    ('GOTAS', 'Test de gotas', 2),
    ('DIGITAL', 'Medidor digital', 3),
    ('LABORATORIO', 'Laboratorio', 4);
CREATE TABLE cat_tipo_mantenimiento (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_tipo_mantenimiento (codigo, nombre, orden) VALUES
    ('CAMBIO_AGUA', 'Cambio de agua', 1),
    ('LIMPIEZA_FILTRO', 'Limpieza de filtro', 2),
    ('CAMBIO_MATERIAL_FILTRANTE', 'Cambio de material filtrante', 3),
    ('LIMPIEZA_CRISTALES', 'Limpieza de cristales', 4),
    ('SIFONADO', 'Sifonado', 5),
    ('PODA', 'Poda', 6),
    ('ABONADO', 'Abonado', 7),
    ('RECARGA_CO2', 'Recarga de CO2', 8),
    ('TEST_AGUA', 'Test de agua', 9),
    ('ALIMENTACION', 'Alimentación', 10),
    ('TRATAMIENTO', 'Tratamiento', 11),
    ('OTRO', 'Otro', 12);
CREATE TABLE cat_resultado_episodio (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_resultado_episodio (codigo, nombre, orden) VALUES
    ('EN_CURSO', 'En curso', 1),
    ('RECUPERADO', 'Recuperado', 2),
    ('FALLECIDO', 'Fallecido', 3),
    ('CRONICO', 'Crónico', 4);
CREATE TABLE cat_categoria_articulo (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_categoria_articulo (codigo, nombre, orden) VALUES
    ('PRIMEROS_PASOS', 'Primeros pasos', 1),
    ('CICLADO', 'Ciclado', 2),
    ('QUIMICA_AGUA', 'Química del agua', 3),
    ('PLANTAS', 'Plantas', 4),
    ('AQUASCAPING', 'Aquascaping', 5),
    ('REPRODUCCION', 'Reproducción', 6),
    ('SALUD', 'Salud', 7),
    ('EQUIPAMIENTO', 'Equipamiento', 8),
    ('ALIMENTACION', 'Alimentación', 9),
    ('BIOTOPOS', 'Biotopos', 10);
CREATE TABLE cat_lista_guardada (
    id           SMALLSERIAL PRIMARY KEY,
    codigo       VARCHAR(30) NOT NULL UNIQUE,
    nombre       VARCHAR(80) NOT NULL,
    descripcion  VARCHAR(255),
    orden        SMALLINT NOT NULL DEFAULT 0,
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);
INSERT INTO cat_lista_guardada (codigo, nombre, orden) VALUES
    ('FAVORITO', 'Favorito', 1),
    ('DESEO', 'Lista de deseos', 2);



-- =====================================================================
-- 1. USUARIOS Y AUTENTICACIÓN (sustituye a django.contrib.auth.User)
-- =====================================================================

CREATE TABLE usuario (
    id                     SERIAL PRIMARY KEY,
    email                  VARCHAR(150) NOT NULL UNIQUE,
    username               VARCHAR(50) NOT NULL UNIQUE,
    password_hash          VARCHAR(255) NOT NULL,              -- BCrypt/Argon2 desde Spring Security
    nombre                 VARCHAR(100),
    apellidos              VARCHAR(150),
    avatar_url             VARCHAR(500),
    id_rol                 SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_rol_usuario(id),   -- 1 = USUARIO
    id_unidad_temperatura  SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_unidad_temperatura(id),   -- 1 = C
    id_unidad_volumen      SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_unidad_volumen(id),   -- 1 = L
    idioma                 VARCHAR(5) NOT NULL DEFAULT 'es',
    activo                 BOOLEAN NOT NULL DEFAULT TRUE,
    email_verificado       BOOLEAN NOT NULL DEFAULT FALSE,
    ultimo_acceso          TIMESTAMP,
    creado_en              TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en         TIMESTAMP NOT NULL DEFAULT now()
);

-- Refresh tokens JWT para la app móvil (se guarda el hash, nunca el token)
CREATE TABLE token_refresco (
    id           SERIAL PRIMARY KEY,
    id_usuario   INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    token_hash   VARCHAR(255) NOT NULL UNIQUE,
    dispositivo  VARCHAR(150),
    expira_en    TIMESTAMP NOT NULL,
    revocado     BOOLEAN NOT NULL DEFAULT FALSE,
    creado_en    TIMESTAMP NOT NULL DEFAULT now()
);

-- Dispositivos para notificaciones push (FCM / APNs): avisos de mantenimiento
CREATE TABLE dispositivo_push (
    id             SERIAL PRIMARY KEY,
    id_usuario     INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    token_push     VARCHAR(500) NOT NULL UNIQUE,
    id_plataforma  SMALLINT NOT NULL REFERENCES cat_plataforma(id),
    activo         BOOLEAN NOT NULL DEFAULT TRUE,
    ultimo_uso     TIMESTAMP,
    creado_en      TIMESTAMP NOT NULL DEFAULT now()
);


-- =====================================================================
-- 2. CATÁLOGOS TAXONÓMICOS Y GEOGRÁFICOS
-- =====================================================================

-- Antes "familia" era texto libre en EspeciePez; ahora es tabla para filtrar y no duplicar
CREATE TABLE familia (
    id                 SERIAL PRIMARY KEY,
    nombre             VARCHAR(100) NOT NULL UNIQUE,           -- Characidae, Cichlidae, Araceae...
    orden              VARCHAR(100),                           -- Characiformes...
    id_tipo_organismo  SMALLINT NOT NULL REFERENCES cat_tipo_organismo(id),
    descripcion        TEXT
);

-- Biotopos / regiones de origen (antes "origen" era texto libre)
CREATE TABLE biotopo (
    id             SERIAL PRIMARY KEY,
    nombre         VARCHAR(150) NOT NULL UNIQUE,           -- "Amazonas - aguas negras", "Lago Malawi"
    id_continente  SMALLINT NOT NULL REFERENCES cat_continente(id),
    id_tipo_agua   SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_tipo_agua(id),   -- 1 = DULCE
    descripcion    TEXT,
    imagen_url     VARCHAR(500)
);


-- =====================================================================
-- 3. ENCICLOPEDIA DE ESPECIES
--    Supertipo "especie" + subtipos (pez, planta, invertebrado).
--    Sustituye al modelo abstracto ParametrosAgua de Django y permite que
--    compatibilidades, imágenes, favoritos o habitantes de un acuario
--    apunten a una única tabla.
--    Las subtablas usan "id" como clave primaria y a la vez FK a especie(id).
--    En JPA: @Inheritance(strategy = InheritanceType.JOINED)
--            @DiscriminatorColumn(name = "id_tipo_organismo", discriminatorType = INTEGER)
-- =====================================================================

CREATE TABLE especie (
    id                 SERIAL PRIMARY KEY,
    id_tipo_organismo  SMALLINT NOT NULL REFERENCES cat_tipo_organismo(id),
    nombre_comun       VARCHAR(150) NOT NULL,
    nombre_cientifico  VARCHAR(150) NOT NULL UNIQUE,
    slug               VARCHAR(160) NOT NULL UNIQUE,       -- para URLs amigables / deep links
    id_familia         INTEGER REFERENCES familia(id) ON DELETE SET NULL,
    id_tipo_agua       SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_tipo_agua(id),   -- 1 = DULCE
    id_dificultad      SMALLINT NOT NULL REFERENCES cat_dificultad(id),
    descripcion        TEXT NOT NULL,

    -- Parámetros de agua (antes ParametrosAgua). Se amplía con KH.
    temperatura_min    NUMERIC(4,1),
    temperatura_max    NUMERIC(4,1),
    ph_min             NUMERIC(3,1),
    ph_max             NUMERIC(3,1),
    gh_min             NUMERIC(4,1),                       -- ºdGH
    gh_max             NUMERIC(4,1),
    kh_min             NUMERIC(4,1),                       -- ºdKH
    kh_max             NUMERIC(4,1),

    publicada          BOOLEAN NOT NULL DEFAULT FALSE, -- flujo editorial: borrador / publicada
    fuentes            TEXT,                               -- bibliografía / referencias
    creado_por         INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    creado_en          TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en     TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT ck_especie_temperatura CHECK (temperatura_min IS NULL OR temperatura_max IS NULL
                                             OR temperatura_min <= temperatura_max),
    CONSTRAINT ck_especie_ph_rango    CHECK (ph_min IS NULL OR ph_max IS NULL OR ph_min <= ph_max),
    CONSTRAINT ck_especie_ph_valores  CHECK ((ph_min IS NULL OR ph_min BETWEEN 0 AND 14)
                                         AND (ph_max IS NULL OR ph_max BETWEEN 0 AND 14)),
    CONSTRAINT ck_especie_gh          CHECK (gh_min IS NULL OR gh_max IS NULL OR (gh_min >= 0 AND gh_min <= gh_max)),
    CONSTRAINT ck_especie_kh          CHECK (kh_min IS NULL OR kh_max IS NULL OR (kh_min >= 0 AND kh_min <= kh_max))
);


-- ---------- 3.1 Peces ----------
CREATE TABLE especie_pez (
    id                          INTEGER PRIMARY KEY REFERENCES especie(id) ON DELETE CASCADE,
    tamano_maximo_cm            NUMERIC(5,1) NOT NULL CHECK (tamano_maximo_cm > 0),
    esperanza_vida_anos         NUMERIC(4,1) CHECK (esperanza_vida_anos > 0),
    volumen_minimo_l            INTEGER NOT NULL CHECK (volumen_minimo_l > 0),
    largo_minimo_acuario_cm     INTEGER CHECK (largo_minimo_acuario_cm > 0),
    id_dieta                    SMALLINT NOT NULL REFERENCES cat_dieta(id),
    id_temperamento             SMALLINT NOT NULL REFERENCES cat_temperamento(id),
    id_nivel_nado               SMALLINT NOT NULL REFERENCES cat_nivel_nado(id),
    es_cardumen                 BOOLEAN NOT NULL DEFAULT FALSE,
    tamano_minimo_grupo         SMALLINT NOT NULL DEFAULT 1 CHECK (tamano_minimo_grupo >= 1),
    come_plantas                BOOLEAN NOT NULL DEFAULT FALSE,
    come_invertebrados          BOOLEAN NOT NULL DEFAULT FALSE,
    es_saltador                 BOOLEAN NOT NULL DEFAULT FALSE,   -- requiere tapa
    id_tipo_reproduccion        SMALLINT REFERENCES cat_tipo_reproduccion(id),
    id_dificultad_reproduccion  SMALLINT REFERENCES cat_dificultad(id),
    dimorfismo_sexual           TEXT,
    notas_reproduccion          TEXT,
    id_estado_conservacion      SMALLINT REFERENCES cat_estado_conservacion(id),   -- categorías UICN
    CONSTRAINT ck_pez_cardumen CHECK (NOT es_cardumen OR tamano_minimo_grupo > 1)
);

-- ---------- 3.2 Plantas ----------
CREATE TABLE especie_planta (
    id                       INTEGER PRIMARY KEY REFERENCES especie(id) ON DELETE CASCADE,
    id_tipo_plantado         SMALLINT NOT NULL REFERENCES cat_tipo_plantado(id),
    id_posicion              SMALLINT REFERENCES cat_posicion_planta(id),
    id_requerimiento_luz     SMALLINT NOT NULL REFERENCES cat_nivel_luz(id),
    requiere_co2             BOOLEAN NOT NULL DEFAULT FALSE,
    id_tasa_crecimiento      SMALLINT NOT NULL REFERENCES cat_tasa_crecimiento(id),   -- antes texto libre
    altura_min_cm            NUMERIC(5,1) CHECK (altura_min_cm >= 0),
    altura_max_cm            NUMERIC(5,1),
    color_predominante       VARCHAR(30),                           -- verde, rojo, marrón...
    requiere_abono_raiz      BOOLEAN NOT NULL DEFAULT FALSE,
    puede_cultivarse_emersa  BOOLEAN NOT NULL DEFAULT FALSE,
    metodo_propagacion       VARCHAR(150),                          -- esquejes, estolones, rizoma...
    CONSTRAINT ck_planta_altura CHECK (altura_min_cm IS NULL OR altura_max_cm IS NULL
                                       OR altura_min_cm <= altura_max_cm)
);

-- ---------- 3.3 Invertebrados (gambas, caracoles, cangrejos...) ----------
CREATE TABLE especie_invertebrado (
    id                       INTEGER PRIMARY KEY REFERENCES especie(id) ON DELETE CASCADE,
    id_grupo                 SMALLINT NOT NULL REFERENCES cat_grupo_invertebrado(id),
    tamano_maximo_cm         NUMERIC(5,1) NOT NULL CHECK (tamano_maximo_cm > 0),
    esperanza_vida_anos      NUMERIC(4,1) CHECK (esperanza_vida_anos > 0),
    volumen_minimo_l         INTEGER NOT NULL CHECK (volumen_minimo_l > 0),
    id_dieta                 SMALLINT NOT NULL REFERENCES cat_dieta(id),
    id_temperamento          SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_temperamento(id),   -- 1 = PACIFICO
    come_plantas             BOOLEAN NOT NULL DEFAULT FALSE,
    sensible_cobre           BOOLEAN NOT NULL DEFAULT TRUE,   -- clave para avisos de medicamentos
    se_reproduce_agua_dulce  BOOLEAN NOT NULL DEFAULT TRUE,   -- p.ej. Caridina multidentata: no
    tamano_minimo_grupo      SMALLINT NOT NULL DEFAULT 1 CHECK (tamano_minimo_grupo >= 1),
    notas_muda               TEXT
);

-- ---------- 3.4 Datos auxiliares de especie ----------
CREATE TABLE especie_nombre_alternativo (
    id                      SERIAL PRIMARY KEY,
    id_especie              INTEGER NOT NULL REFERENCES especie(id) ON DELETE CASCADE,
    nombre                  VARCHAR(150) NOT NULL,
    idioma                  VARCHAR(5) NOT NULL DEFAULT 'es',
    es_sinonimo_cientifico  BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE (id_especie, nombre, idioma)
);

-- Galería de imágenes (sustituye a imagen_url única)
CREATE TABLE especie_imagen (
    id            SERIAL PRIMARY KEY,
    id_especie    INTEGER NOT NULL REFERENCES especie(id) ON DELETE CASCADE,
    url           VARCHAR(500) NOT NULL,
    descripcion   VARCHAR(255),                  -- "macho adulto", "variedad red cherry"...
    autor         VARCHAR(150),
    licencia      VARCHAR(50),                   -- CC BY-SA 4.0, propia...
    es_principal  BOOLEAN NOT NULL DEFAULT FALSE,
    orden         SMALLINT NOT NULL DEFAULT 0
);
-- Solo debe haber una imagen principal por especie: se controla en el servicio Java

CREATE TABLE especie_biotopo (
    id_especie  INTEGER NOT NULL REFERENCES especie(id) ON DELETE CASCADE,
    id_biotopo  INTEGER NOT NULL REFERENCES biotopo(id) ON DELETE CASCADE,
    PRIMARY KEY (id_especie, id_biotopo)
);

-- ---------- 3.5 Compatibilidades ----------
-- Sirve entre cualquier tipo de especie (pez-pez, pez-gamba, pez-planta).
-- Consejo para el servicio Java: guardar siempre la pareja con el id menor en
-- id_especie_a, para no registrar (A,B) y (B,A) por duplicado.
CREATE TABLE compatibilidad (
    id              SERIAL PRIMARY KEY,
    id_especie_a    INTEGER NOT NULL REFERENCES especie(id) ON DELETE CASCADE,
    id_especie_b    INTEGER NOT NULL REFERENCES especie(id) ON DELETE CASCADE,
    id_estado       SMALLINT NOT NULL REFERENCES cat_estado_compatibilidad(id),
    id_motivo       SMALLINT REFERENCES cat_motivo_compatibilidad(id),
    notas           TEXT,
    creado_en       TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en  TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT ck_compatibilidad_orden CHECK (id_especie_a <> id_especie_b),
    CONSTRAINT ux_compatibilidad UNIQUE (id_especie_a, id_especie_b)
);



-- =====================================================================
-- 4. ALIMENTACIÓN
-- =====================================================================

CREATE TABLE marca (
    id        SERIAL PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL UNIQUE,
    pais      VARCHAR(60),
    web_url   VARCHAR(300),
    logo_url  VARCHAR(500)
);

CREATE TABLE alimento (
    id           SERIAL PRIMARY KEY,
    nombre       VARCHAR(150) NOT NULL,                  -- "Artemia congelada", "Escamas spirulina"
    id_tipo      SMALLINT NOT NULL REFERENCES cat_tipo_alimento(id),
    id_marca     INTEGER REFERENCES marca(id) ON DELETE SET NULL,
    descripcion  TEXT,
    imagen_url   VARCHAR(500),
    CONSTRAINT ux_alimento_nombre_marca UNIQUE (nombre, id_marca)
);

CREATE TABLE especie_alimento (
    id            SERIAL PRIMARY KEY,
    id_especie    INTEGER NOT NULL REFERENCES especie(id)  ON DELETE CASCADE,
    id_alimento   INTEGER NOT NULL REFERENCES alimento(id) ON DELETE CASCADE,
    es_principal  BOOLEAN NOT NULL DEFAULT FALSE,
    frecuencia    VARCHAR(100),                           -- "1-2 veces al día", "como premio semanal"
    CONSTRAINT ux_especie_alimento UNIQUE (id_especie, id_alimento)
);


-- =====================================================================
-- 5. SALUD: ENFERMEDADES, SÍNTOMAS Y MEDICAMENTOS
-- =====================================================================

CREATE TABLE enfermedad (
    id              SERIAL PRIMARY KEY,
    nombre          VARCHAR(150) NOT NULL UNIQUE,
    nombre_tecnico  VARCHAR(150),                           -- "Ichthyophthirius multifiliis"
    id_agente       SMALLINT NOT NULL REFERENCES cat_agente_enfermedad(id),
    id_gravedad     SMALLINT NOT NULL REFERENCES cat_gravedad(id),
    es_contagiosa   BOOLEAN NOT NULL DEFAULT FALSE,
    descripcion     TEXT NOT NULL,
    causas          TEXT,
    tratamiento     TEXT,
    prevencion      TEXT,
    imagen_url      VARCHAR(500),
    creado_en       TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE sintoma (
    id           SERIAL PRIMARY KEY,
    nombre       VARCHAR(150) NOT NULL UNIQUE,
    id_zona      SMALLINT NOT NULL REFERENCES cat_zona_sintoma(id),
    descripcion  TEXT
);

-- Qué síntomas tiene cada enfermedad (permite programar en Java un asistente de diagnóstico)
CREATE TABLE enfermedad_sintoma (
    id                 SERIAL PRIMARY KEY,
    id_enfermedad      INTEGER NOT NULL REFERENCES enfermedad(id) ON DELETE CASCADE,
    id_sintoma         INTEGER NOT NULL REFERENCES sintoma(id)    ON DELETE CASCADE,
    es_caracteristico  BOOLEAN NOT NULL DEFAULT FALSE,     -- síntoma casi exclusivo de la enfermedad
    CONSTRAINT ux_enfermedad_sintoma UNIQUE (id_enfermedad, id_sintoma)
);

CREATE TABLE medicamento (
    id                       SERIAL PRIMARY KEY,
    nombre                   VARCHAR(150) NOT NULL,
    principio_activo         VARCHAR(150),                   -- verde malaquita, sulfato de cobre...
    id_marca                 INTEGER REFERENCES marca(id) ON DELETE SET NULL,
    descripcion              TEXT,
    seguro_invertebrados     BOOLEAN NOT NULL DEFAULT FALSE,
    seguro_plantas           BOOLEAN NOT NULL DEFAULT TRUE,
    afecta_filtro_biologico  BOOLEAN NOT NULL DEFAULT FALSE,
    contiene_cobre           BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT ux_medicamento_nombre_marca UNIQUE (nombre, id_marca)
);

CREATE TABLE enfermedad_medicamento (
    id              SERIAL PRIMARY KEY,
    id_enfermedad   INTEGER NOT NULL REFERENCES enfermedad(id)  ON DELETE CASCADE,
    id_medicamento  INTEGER NOT NULL REFERENCES medicamento(id) ON DELETE CASCADE,
    notas           TEXT,                                   -- según prospecto del fabricante
    CONSTRAINT ux_enfermedad_medicamento UNIQUE (id_enfermedad, id_medicamento)
);

-- Especies especialmente propensas a una enfermedad
CREATE TABLE especie_enfermedad (
    id             SERIAL PRIMARY KEY,
    id_especie     INTEGER NOT NULL REFERENCES especie(id)    ON DELETE CASCADE,
    id_enfermedad  INTEGER NOT NULL REFERENCES enfermedad(id) ON DELETE CASCADE,
    notas          TEXT,
    CONSTRAINT ux_especie_enfermedad UNIQUE (id_especie, id_enfermedad)
);


-- =====================================================================
-- 6. EQUIPAMIENTO Y MATERIALES
-- =====================================================================

-- Categorías jerárquicas: Filtración > Filtro externo; Decoración > Roca...
CREATE TABLE categoria_equipamiento (
    id                  SERIAL PRIMARY KEY,
    nombre              VARCHAR(100) NOT NULL,
    id_categoria_padre  INTEGER REFERENCES categoria_equipamiento(id) ON DELETE RESTRICT,
    descripcion         TEXT,
    icono               VARCHAR(50),                        -- nombre de icono para la app
    orden               SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT ux_categoria_nombre_padre UNIQUE (nombre, id_categoria_padre)
);

CREATE TABLE equipamiento (
    id                     SERIAL PRIMARY KEY,
    id_categoria           INTEGER NOT NULL REFERENCES categoria_equipamiento(id) ON DELETE RESTRICT,
    id_marca               INTEGER REFERENCES marca(id) ON DELETE RESTRICT,
    modelo                 VARCHAR(150) NOT NULL,          -- en elementos naturales: "Roca Seiryu"
    descripcion            TEXT NOT NULL,
    es_natural             BOOLEAN NOT NULL DEFAULT FALSE,
    altera_parametros      VARCHAR(100),                   -- "sube GH/KH", "acidifica" (rocas, troncos, sustratos)
    caudal_l_h             INTEGER CHECK (caudal_l_h > 0),
    potencia_w             NUMERIC(6,1) CHECK (potencia_w > 0),
    volumen_acuario_min_l  INTEGER CHECK (volumen_acuario_min_l > 0),
    volumen_acuario_max_l  INTEGER,
    especificaciones       TEXT,                           -- datos técnicos en texto libre (lúmenes, etapas...)
    imagen_url             VARCHAR(500),
    creado_en              TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en         TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT ck_equipamiento_marca   CHECK (es_natural OR id_marca IS NOT NULL),
    CONSTRAINT ck_equipamiento_volumen CHECK (volumen_acuario_min_l IS NULL OR volumen_acuario_max_l IS NULL
                                              OR volumen_acuario_min_l <= volumen_acuario_max_l)
);


-- =====================================================================
-- 7. ACUARIOS DE USUARIO (configurador)
-- =====================================================================

CREATE TABLE acuario (
    id                 SERIAL PRIMARY KEY,
    id_usuario         INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    nombre             VARCHAR(150) NOT NULL,
    id_tipo_agua       SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_tipo_agua(id),   -- 1 = DULCE
    id_estilo          SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_estilo_acuario(id),   -- 1 = COMUNITARIO
    id_biotopo         INTEGER REFERENCES biotopo(id) ON DELETE SET NULL,
    volumen_bruto_l    INTEGER CHECK (volumen_bruto_l > 0),
    volumen_neto_l     INTEGER NOT NULL CHECK (volumen_neto_l > 0),
    largo_cm           INTEGER CHECK (largo_cm > 0),
    ancho_cm           INTEGER CHECK (ancho_cm > 0),
    alto_cm            INTEGER CHECK (alto_cm > 0),
    fecha_montaje      DATE NOT NULL,
    fecha_desmontaje   DATE,
    id_estado          SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_estado_acuario(id),   -- 1 = CICLANDO
    con_co2            BOOLEAN NOT NULL DEFAULT FALSE,
    fotoperiodo_horas  NUMERIC(3,1) CHECK (fotoperiodo_horas BETWEEN 0 AND 24),
    foto_url           VARCHAR(500),
    notas              TEXT,
    es_publico         BOOLEAN NOT NULL DEFAULT FALSE,   -- por si luego hay galería comunitaria
    creado_en          TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en     TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT ck_acuario_volumen CHECK (volumen_bruto_l IS NULL OR volumen_neto_l <= volumen_bruto_l),
    CONSTRAINT ck_acuario_fechas  CHECK (fecha_desmontaje IS NULL OR fecha_desmontaje >= fecha_montaje)
);

-- Habitantes: une PezAcuario y PlantaAcuario de Django (y añade invertebrados).
-- Guarda historial: fecha_baja NULL = sigue en el acuario.
CREATE TABLE acuario_habitante (
    id                    SERIAL PRIMARY KEY,
    id_acuario            INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    id_especie            INTEGER NOT NULL REFERENCES especie(id) ON DELETE RESTRICT,
    cantidad              INTEGER CHECK (cantidad >= 1),            -- peces / invertebrados
    cantidad_descripcion  VARCHAR(50),                                   -- plantas: "3 macetas", "1 tapizado"
    variedad              VARCHAR(100),                                  -- "Red cherry", "Halfmoon"...
    machos                SMALLINT CHECK (machos >= 0),
    hembras               SMALLINT CHECK (hembras >= 0),
    fecha_alta            DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_baja            DATE,
    id_motivo_baja        SMALLINT REFERENCES cat_motivo_baja(id),
    notas                 VARCHAR(255),
    creado_en             TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en        TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT ck_habitante_cantidad CHECK (cantidad IS NOT NULL OR cantidad_descripcion IS NOT NULL),
    CONSTRAINT ck_habitante_baja     CHECK ((fecha_baja IS NULL AND id_motivo_baja IS NULL)
                                         OR (fecha_baja IS NOT NULL AND id_motivo_baja IS NOT NULL)),
    CONSTRAINT ck_habitante_fechas   CHECK (fecha_baja IS NULL OR fecha_baja >= fecha_alta)
);

CREATE TABLE acuario_equipamiento (
    id                 SERIAL PRIMARY KEY,
    id_acuario         INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    id_equipamiento    INTEGER REFERENCES equipamiento(id) ON DELETE RESTRICT,
    descripcion_libre  VARCHAR(150),                       -- si el equipo no está en el catálogo
    cantidad           SMALLINT NOT NULL DEFAULT 1 CHECK (cantidad >= 1),
    fecha_instalacion  DATE,
    fecha_retirada     DATE,
    notas              VARCHAR(255),
    CONSTRAINT ck_acuario_equipo_origen CHECK (id_equipamiento IS NOT NULL OR descripcion_libre IS NOT NULL)
);


-- =====================================================================
-- 8. SEGUIMIENTO: TESTS DE AGUA, MANTENIMIENTO, SALUD Y DIARIO
-- =====================================================================

-- Antes RegistroTestAgua. La fecha la indica el usuario (puede registrar a posteriori).
CREATE TABLE medicion_agua (
    id                   SERIAL PRIMARY KEY,
    id_acuario           INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    medido_en            TIMESTAMP NOT NULL DEFAULT now(),
    id_metodo            SMALLINT REFERENCES cat_metodo_medicion(id),
    temperatura          NUMERIC(4,1),
    ph                   NUMERIC(3,1) CHECK (ph BETWEEN 0 AND 14),
    gh                   NUMERIC(4,1) CHECK (gh >= 0),
    kh                   NUMERIC(4,1) CHECK (kh >= 0),
    amoniaco_mg_l        NUMERIC(5,2) CHECK (amoniaco_mg_l >= 0),
    nitrito_mg_l         NUMERIC(5,2) CHECK (nitrito_mg_l >= 0),
    nitrato_mg_l         NUMERIC(5,1) CHECK (nitrato_mg_l >= 0),
    fosfato_mg_l         NUMERIC(5,2) CHECK (fosfato_mg_l >= 0),
    hierro_mg_l          NUMERIC(5,2) CHECK (hierro_mg_l >= 0),
    cobre_mg_l           NUMERIC(5,2) CHECK (cobre_mg_l >= 0),
    co2_mg_l             NUMERIC(5,1) CHECK (co2_mg_l >= 0),
    oxigeno_mg_l         NUMERIC(4,1) CHECK (oxigeno_mg_l >= 0),
    tds_ppm              INTEGER CHECK (tds_ppm >= 0),
    conductividad_us_cm  INTEGER CHECK (conductividad_us_cm >= 0),
    densidad             NUMERIC(5,4) CHECK (densidad BETWEEN 0.9 AND 1.1),  -- salobre / marino
    notas                TEXT,
    creado_en            TIMESTAMP NOT NULL DEFAULT now()
);

-- Tareas recurrentes que generan avisos push ("Cambio de agua cada 7 días")
CREATE TABLE tarea_programada (
    id              SERIAL PRIMARY KEY,
    id_acuario      INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    id_tipo         SMALLINT NOT NULL REFERENCES cat_tipo_mantenimiento(id),
    titulo          VARCHAR(150) NOT NULL,
    descripcion     TEXT,
    cada_n_dias     SMALLINT CHECK (cada_n_dias > 0),   -- NULL = tarea puntual
    proxima_fecha   DATE NOT NULL,
    hora_aviso      TIME,
    notificar       BOOLEAN NOT NULL DEFAULT TRUE,
    activa          BOOLEAN NOT NULL DEFAULT TRUE,
    creado_en       TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en  TIMESTAMP NOT NULL DEFAULT now()
);

-- Registro de lo realizado (histórico)
CREATE TABLE registro_mantenimiento (
    id                      SERIAL PRIMARY KEY,
    id_acuario              INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    id_tarea                INTEGER REFERENCES tarea_programada(id) ON DELETE SET NULL,
    id_tipo                 SMALLINT NOT NULL REFERENCES cat_tipo_mantenimiento(id),
    realizado_en            TIMESTAMP NOT NULL DEFAULT now(),
    porcentaje_cambio_agua  SMALLINT CHECK (porcentaje_cambio_agua BETWEEN 1 AND 100),
    producto                VARCHAR(150),                   -- abono, acondicionador...
    dosis                   VARCHAR(50),
    notas                   TEXT,
    creado_en               TIMESTAMP NOT NULL DEFAULT now()
);

-- Al registrar un mantenimiento asociado a una tarea, el servicio Java
-- recalcula proxima_fecha = fecha realizada + cada_n_dias

-- Episodios de enfermedad en un acuario concreto
CREATE TABLE episodio_salud (
    id                     SERIAL PRIMARY KEY,
    id_acuario             INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    id_especie             INTEGER REFERENCES especie(id)     ON DELETE SET NULL,
    id_enfermedad          INTEGER REFERENCES enfermedad(id)  ON DELETE SET NULL,  -- NULL = sin diagnosticar
    id_medicamento         INTEGER REFERENCES medicamento(id) ON DELETE SET NULL,
    fecha_inicio           DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_fin              DATE,
    individuos_afectados   SMALLINT CHECK (individuos_afectados >= 0),
    individuos_fallecidos  SMALLINT CHECK (individuos_fallecidos >= 0),
    tratamiento_aplicado   TEXT,
    id_resultado           SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_resultado_episodio(id),   -- 1 = EN_CURSO
    notas                  TEXT,
    creado_en              TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en         TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT ck_episodio_fechas CHECK (fecha_fin IS NULL OR fecha_fin >= fecha_inicio)
);

-- Diario del acuario (bitácora con fotos)
CREATE TABLE diario_entrada (
    id              SERIAL PRIMARY KEY,
    id_acuario      INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    fecha           TIMESTAMP NOT NULL DEFAULT now(),
    titulo          VARCHAR(150),
    contenido       TEXT NOT NULL,
    creado_en       TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE acuario_foto (
    id                 SERIAL PRIMARY KEY,
    id_acuario         INTEGER NOT NULL REFERENCES acuario(id) ON DELETE CASCADE,
    id_diario_entrada  INTEGER REFERENCES diario_entrada(id) ON DELETE SET NULL,
    url                VARCHAR(500) NOT NULL,
    descripcion        VARCHAR(255),
    tomada_en          TIMESTAMP NOT NULL DEFAULT now()
);


-- =====================================================================
-- 9. CONTENIDO DIVULGATIVO Y USUARIO
-- =====================================================================

CREATE TABLE articulo (
    id                  SERIAL PRIMARY KEY,
    titulo              VARCHAR(200) NOT NULL,
    slug                VARCHAR(220) NOT NULL UNIQUE,
    id_categoria        SMALLINT NOT NULL REFERENCES cat_categoria_articulo(id),
    resumen             VARCHAR(500),
    contenido_md        TEXT NOT NULL,              -- Markdown: fácil de renderizar en móvil
    imagen_portada_url  VARCHAR(500),
    id_autor            INTEGER REFERENCES usuario(id) ON DELETE SET NULL,
    publicado           BOOLEAN NOT NULL DEFAULT FALSE,
    publicado_en        TIMESTAMP,
    creado_en           TIMESTAMP NOT NULL DEFAULT now(),
    actualizado_en      TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE articulo_especie (
    id_articulo  INTEGER NOT NULL REFERENCES articulo(id) ON DELETE CASCADE,
    id_especie   INTEGER NOT NULL REFERENCES especie(id)  ON DELETE CASCADE,
    PRIMARY KEY (id_articulo, id_especie)
);

CREATE TABLE glosario_termino (
    id           SERIAL PRIMARY KEY,
    termino      VARCHAR(100) NOT NULL UNIQUE,           -- "Ciclo del nitrógeno", "KH", "Iwagumi"
    definicion   TEXT NOT NULL,
    id_articulo  INTEGER REFERENCES articulo(id) ON DELETE SET NULL
);

-- Favoritos y lista de deseos del usuario
CREATE TABLE usuario_especie_guardada (
    id          SERIAL PRIMARY KEY,
    id_usuario  INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    id_especie  INTEGER NOT NULL REFERENCES especie(id) ON DELETE CASCADE,
    id_lista    SMALLINT NOT NULL DEFAULT 1 REFERENCES cat_lista_guardada(id),   -- 1 = FAVORITO
    creado_en   TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT ux_usuario_especie_guardada UNIQUE (id_usuario, id_especie, id_lista)
);



-- =====================================================================
-- 10. DATOS DE EJEMPLO
--     · Las opciones de catálogo se ponen por su id (con el código en comentario).
--       Los ids son fijos porque los catálogos se rellenan en el orden de la sección 0.
--     · El resto de relaciones se buscan con una subconsulta por nombre.
--     (valores orientativos; revisar con bibliografía antes de producción)
-- =====================================================================

-- ---------- Categorías de equipamiento ----------
INSERT INTO categoria_equipamiento (nombre, icono, orden) VALUES
    ('Filtración',          'filter',      1),
    ('Iluminación',         'light',       2),
    ('Calefacción',         'thermometer', 3),
    ('Sustrato',            'layers',      4),
    ('CO2 y fertilización', 'bubbles',     5),
    ('Aireación',           'wind',        6),
    ('Decoración natural',  'mountain',    7),
    ('Test y medición',     'flask',       8),
    ('Mantenimiento',       'tool',        9);

-- Subcategorías: su padre se busca por nombre
INSERT INTO categoria_equipamiento (nombre, id_categoria_padre, orden) VALUES
    ('Filtro externo', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Filtración'), 1),
    ('Filtro interno', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Filtración'), 2),
    ('Filtro mochila', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Filtración'), 3),
    ('Filtro de esponja', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Filtración'), 4),
    ('Material filtrante', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Filtración'), 5),
    ('Pantalla LED', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Iluminación'), 1),
    ('Termocalentador', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Calefacción'), 1),
    ('Sustrato nutritivo', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Sustrato'), 1),
    ('Grava / arena inerte', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Sustrato'), 2),
    ('Kit CO2 presurizado', (SELECT id FROM categoria_equipamiento WHERE nombre = 'CO2 y fertilización'), 1),
    ('Abono líquido', (SELECT id FROM categoria_equipamiento WHERE nombre = 'CO2 y fertilización'), 2),
    ('Roca', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Decoración natural'), 1),
    ('Tronco / raíz', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Decoración natural'), 2),
    ('Test de gotas', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Test y medición'), 1),
    ('Tiras reactivas', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Test y medición'), 2),
    ('Medidor digital', (SELECT id FROM categoria_equipamiento WHERE nombre = 'Test y medición'), 3);

-- ---------- Familias ----------
INSERT INTO familia (nombre, orden, id_tipo_organismo) VALUES
    ('Characidae',     'Characiformes',      1 /* PEZ */),
    ('Callichthyidae', 'Siluriformes',       1 /* PEZ */),
    ('Osphronemidae',  'Anabantiformes',     1 /* PEZ */),
    ('Cichlidae',      'Cichliformes',       1 /* PEZ */),
    ('Poeciliidae',    'Cyprinodontiformes', 1 /* PEZ */),
    ('Atyidae',        'Decapoda',           3 /* INVERTEBRADO */),
    ('Araceae',        'Alismatales',        2 /* PLANTA */);

-- ---------- Biotopos ----------
INSERT INTO biotopo (nombre, id_continente, descripcion) VALUES
    ('Amazonas - aguas negras',          1 /* AMERICA_SUR */,
     'Aguas blandas y ácidas teñidas por taninos, luz tenue y fondo de hojarasca.'),
    ('Cuenca del Paraná',                1 /* AMERICA_SUR */,
     'Ríos subtropicales con fondos arenosos y temperaturas algo más frescas.'),
    ('Arrozales del Sudeste Asiático',   5 /* ASIA */,
     'Aguas someras, cálidas y estancadas con abundante vegetación.'),
    ('Arroyos de Taiwán y sur de China', 5 /* ASIA */,
     'Cursos de agua con vegetación densa y parámetros moderados.'),
    ('Ríos de África occidental',        4 /* AFRICA */,
     'Orillas sombreadas con plantas ancladas a rocas y raíces.');

-- ---------- Especies ----------
-- Primero se inserta la fila común en "especie" y después la de su subtabla,
-- que usa el MISMO id (se busca por el nombre científico).

-- Tetra neón
INSERT INTO especie (id_tipo_organismo, nombre_comun, nombre_cientifico, slug, id_familia, id_dificultad, descripcion,
                     temperatura_min, temperatura_max, ph_min, ph_max, gh_min, gh_max, kh_min, kh_max, publicada)
VALUES (1 /* PEZ */, 'Tetra neón', 'Paracheirodon innesi', 'paracheirodon-innesi',
        (SELECT id FROM familia WHERE nombre = 'Characidae'), 1 /* FACIL */,
        'Pequeño carácido de cardumen con una línea azul iridiscente y rojo en la mitad posterior. Muy popular en acuarios comunitarios plantados.',
        20, 26, 5.0, 7.0, 1, 10, 1, 4, TRUE);

INSERT INTO especie_pez (id, tamano_maximo_cm, esperanza_vida_anos, volumen_minimo_l, id_dieta, id_temperamento,
                         id_nivel_nado, es_cardumen, tamano_minimo_grupo, id_tipo_reproduccion,
                         id_dificultad_reproduccion, dimorfismo_sexual)
VALUES ((SELECT id FROM especie WHERE nombre_cientifico = 'Paracheirodon innesi'), 4, 6, 60,
        3 /* OMNIVORO */, 1 /* PACIFICO */, 2 /* MEDIO */, TRUE, 10,
        1 /* OVIPARO_DISPERSOR */, 3 /* DIFICIL */,
        'Hembras más redondeadas; la línea azul se curva en ellas.');

-- Corydora pimienta
INSERT INTO especie (id_tipo_organismo, nombre_comun, nombre_cientifico, slug, id_familia, id_dificultad, descripcion,
                     temperatura_min, temperatura_max, ph_min, ph_max, gh_min, gh_max, publicada)
VALUES (1 /* PEZ */, 'Corydora pimienta', 'Corydoras paleatus', 'corydoras-paleatus',
        (SELECT id FROM familia WHERE nombre = 'Callichthyidae'), 1 /* FACIL */,
        'Pez de fondo acorazado y gregario que remueve el sustrato en busca de alimento. Necesita arena fina para no dañar sus barbillones.',
        18, 26, 6.0, 8.0, 2, 20, TRUE);

INSERT INTO especie_pez (id, tamano_maximo_cm, esperanza_vida_anos, volumen_minimo_l, id_dieta, id_temperamento,
                         id_nivel_nado, es_cardumen, tamano_minimo_grupo, id_tipo_reproduccion,
                         id_dificultad_reproduccion)
VALUES ((SELECT id FROM especie WHERE nombre_cientifico = 'Corydoras paleatus'), 7, 8, 60,
        3 /* OMNIVORO */, 1 /* PACIFICO */, 3 /* FONDO */, TRUE, 6,
        2 /* OVIPARO_ADHERENTE */, 2 /* MEDIA */);

-- Luchador de Siam
INSERT INTO especie (id_tipo_organismo, nombre_comun, nombre_cientifico, slug, id_familia, id_dificultad, descripcion,
                     temperatura_min, temperatura_max, ph_min, ph_max, gh_min, gh_max, publicada)
VALUES (1 /* PEZ */, 'Luchador de Siam', 'Betta splendens', 'betta-splendens',
        (SELECT id FROM familia WHERE nombre = 'Osphronemidae'), 1 /* FACIL */,
        'Anabántido con órgano laberinto que le permite respirar aire atmosférico. Los machos son muy territoriales entre sí.',
        24, 30, 6.0, 8.0, 5, 20, TRUE);

INSERT INTO especie_pez (id, tamano_maximo_cm, esperanza_vida_anos, volumen_minimo_l, id_dieta, id_temperamento,
                         id_nivel_nado, es_cardumen, tamano_minimo_grupo, come_invertebrados, es_saltador,
                         id_tipo_reproduccion, id_dificultad_reproduccion, id_estado_conservacion)
VALUES ((SELECT id FROM especie WHERE nombre_cientifico = 'Betta splendens'), 6.5, 3, 20,
        2 /* CARNIVORO */, 4 /* TERRITORIAL */, 1 /* SUPERFICIE */, FALSE, 1, TRUE, TRUE,
        3 /* NIDO_BURBUJAS */, 2 /* MEDIA */, 5 /* VU */);

-- Gamba cherry
INSERT INTO especie (id_tipo_organismo, nombre_comun, nombre_cientifico, slug, id_familia, id_dificultad, descripcion,
                     temperatura_min, temperatura_max, ph_min, ph_max, gh_min, gh_max, kh_min, kh_max, publicada)
VALUES (3 /* INVERTEBRADO */, 'Gamba cherry', 'Neocaridina davidi', 'neocaridina-davidi',
        (SELECT id FROM familia WHERE nombre = 'Atyidae'), 1 /* FACIL */,
        'Gamba de agua dulce muy resistente, seleccionada en múltiples variedades de color. Excelente limpiadora de algas y restos.',
        18, 28, 6.5, 8.0, 6, 15, 2, 8, TRUE);

INSERT INTO especie_invertebrado (id, id_grupo, tamano_maximo_cm, esperanza_vida_anos, volumen_minimo_l,
                                  id_dieta, sensible_cobre, se_reproduce_agua_dulce, tamano_minimo_grupo)
VALUES ((SELECT id FROM especie WHERE nombre_cientifico = 'Neocaridina davidi'), 1 /* GAMBA */, 3, 1.5, 10,
        3 /* OMNIVORO */, TRUE, TRUE, 10);

-- Anubias nana
INSERT INTO especie (id_tipo_organismo, nombre_comun, nombre_cientifico, slug, id_familia, id_dificultad, descripcion,
                     temperatura_min, temperatura_max, ph_min, ph_max, gh_min, gh_max, publicada)
VALUES (2 /* PLANTA */, 'Anubias nana', 'Anubias barteri var. nana', 'anubias-barteri-nana',
        (SELECT id FROM familia WHERE nombre = 'Araceae'), 1 /* FACIL */,
        'Planta robusta de hojas coriáceas que se ata a rocas o troncos. El rizoma no debe enterrarse.',
        22, 28, 6.0, 7.5, 3, 15, TRUE);

INSERT INTO especie_planta (id, id_tipo_plantado, id_posicion, id_requerimiento_luz, requiere_co2,
                            id_tasa_crecimiento, altura_min_cm, altura_max_cm, color_predominante,
                            puede_cultivarse_emersa, metodo_propagacion)
VALUES ((SELECT id FROM especie WHERE nombre_cientifico = 'Anubias barteri var. nana'), 3 /* RIZOMA */, 1 /* DELANTERA */,
        1 /* BAJA */, FALSE, 1 /* MUY_LENTA */,
        5, 10, 'verde', TRUE, 'División del rizoma');

-- ---------- Especie - biotopo ----------
INSERT INTO especie_biotopo (id_especie, id_biotopo) VALUES
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Paracheirodon innesi'),      (SELECT id FROM biotopo WHERE nombre = 'Amazonas - aguas negras')),
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Corydoras paleatus'),        (SELECT id FROM biotopo WHERE nombre = 'Cuenca del Paraná')),
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Betta splendens'),           (SELECT id FROM biotopo WHERE nombre = 'Arrozales del Sudeste Asiático')),
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Neocaridina davidi'),        (SELECT id FROM biotopo WHERE nombre = 'Arroyos de Taiwán y sur de China')),
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Anubias barteri var. nana'), (SELECT id FROM biotopo WHERE nombre = 'Ríos de África occidental'));

-- ---------- Compatibilidades ----------
INSERT INTO compatibilidad (id_especie_a, id_especie_b, id_estado, id_motivo, notas) VALUES
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Paracheirodon innesi'), (SELECT id FROM especie WHERE nombre_cientifico = 'Corydoras paleatus'),
     1 /* IDEAL */, NULL,
     'Ocupan niveles distintos y comparten parámetros.'),
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Betta splendens'), (SELECT id FROM especie WHERE nombre_cientifico = 'Neocaridina davidi'),
     2 /* PRECAUCION */, 1 /* DEPREDACION */,
     'El betta puede cazar gambas juveniles; ofrecer mucha vegetación densa.'),
    ((SELECT id FROM especie WHERE nombre_cientifico = 'Paracheirodon innesi'), (SELECT id FROM especie WHERE nombre_cientifico = 'Betta splendens'),
     2 /* PRECAUCION */, 3 /* PARAMETROS_AGUA */,
     'Solapan temperatura en un margen estrecho (24-26 ºC).');

-- ---------- Salud ----------
INSERT INTO sintoma (nombre, id_zona) VALUES
    ('Puntos blancos como granos de sal', 1 /* PIEL */),
    ('Se roza contra objetos', 7 /* COMPORTAMIENTO */),
    ('Aletas plegadas', 2 /* ALETAS */),
    ('Respiración acelerada o boqueo en superficie', 3 /* BRANQUIAS */),
    ('Pérdida de apetito', 8 /* GENERAL */),
    ('Masas algodonosas blanquecinas', 1 /* PIEL */),
    ('Bordes de las aletas deshilachados', 2 /* ALETAS */),
    ('Branquias enrojecidas', 3 /* BRANQUIAS */),
    ('Letargo', 7 /* COMPORTAMIENTO */);

INSERT INTO enfermedad (nombre, nombre_tecnico, id_agente, id_gravedad, es_contagiosa, descripcion, prevencion) VALUES
    ('Punto blanco', 'Ichthyophthirius multifiliis',
     3 /* PARASITO_EXTERNO */, 2 /* MODERADA */, TRUE,
     'Protozoo que forma quistes visibles en piel y aletas. Muy frecuente tras cambios bruscos de temperatura o introducción de peces nuevos.',
     'Cuarentena de nuevas incorporaciones y estabilidad térmica.'),
    ('Saprolegniasis', 'Saprolegnia spp.',
     2 /* HONGO */, 2 /* MODERADA */, FALSE,
     'Infección fúngica oportunista que aparece sobre heridas o tejidos debilitados.',
     'Buena calidad de agua y evitar lesiones por manipulación.'),
    ('Podredumbre de aletas', NULL,
     1 /* BACTERIA */, 2 /* MODERADA */, TRUE,
     'Infección bacteriana que degrada progresivamente las aletas, asociada a mala calidad del agua o estrés.',
     'Cambios de agua regulares y evitar compañeros que muerdan aletas.'),
    ('Intoxicación por amoniaco / nitritos', NULL,
     6 /* AMBIENTAL */, 3 /* GRAVE */, FALSE,
     'Acumulación de compuestos nitrogenados por acuario sin ciclar, sobrepoblación o fallo del filtro.',
     'Ciclar el acuario antes de introducir fauna y no sobrealimentar.');

INSERT INTO enfermedad_sintoma (id_enfermedad, id_sintoma, es_caracteristico) VALUES
    ((SELECT id FROM enfermedad WHERE nombre = 'Punto blanco'),
     (SELECT id FROM sintoma WHERE nombre = 'Puntos blancos como granos de sal'), TRUE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Punto blanco'),
     (SELECT id FROM sintoma WHERE nombre = 'Se roza contra objetos'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Punto blanco'),
     (SELECT id FROM sintoma WHERE nombre = 'Aletas plegadas'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Punto blanco'),
     (SELECT id FROM sintoma WHERE nombre = 'Respiración acelerada o boqueo en superficie'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Punto blanco'),
     (SELECT id FROM sintoma WHERE nombre = 'Pérdida de apetito'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Saprolegniasis'),
     (SELECT id FROM sintoma WHERE nombre = 'Masas algodonosas blanquecinas'), TRUE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Saprolegniasis'),
     (SELECT id FROM sintoma WHERE nombre = 'Letargo'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Saprolegniasis'),
     (SELECT id FROM sintoma WHERE nombre = 'Pérdida de apetito'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Podredumbre de aletas'),
     (SELECT id FROM sintoma WHERE nombre = 'Bordes de las aletas deshilachados'), TRUE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Podredumbre de aletas'),
     (SELECT id FROM sintoma WHERE nombre = 'Aletas plegadas'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Podredumbre de aletas'),
     (SELECT id FROM sintoma WHERE nombre = 'Letargo'), FALSE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Intoxicación por amoniaco / nitritos'),
     (SELECT id FROM sintoma WHERE nombre = 'Branquias enrojecidas'), TRUE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Intoxicación por amoniaco / nitritos'),
     (SELECT id FROM sintoma WHERE nombre = 'Respiración acelerada o boqueo en superficie'), TRUE),
    ((SELECT id FROM enfermedad WHERE nombre = 'Intoxicación por amoniaco / nitritos'),
     (SELECT id FROM sintoma WHERE nombre = 'Letargo'), FALSE);

-- ---------- Glosario básico ----------
INSERT INTO glosario_termino (termino, definicion) VALUES
    ('Ciclado', 'Proceso de establecer colonias de bacterias nitrificantes que transforman amoniaco en nitrito y nitrito en nitrato antes de introducir fauna.'),
    ('GH', 'Dureza general: concentración de calcio y magnesio disueltos, expresada en grados alemanes (ºdGH).'),
    ('KH', 'Dureza carbonatada: capacidad tampón del agua frente a cambios de pH, en grados alemanes (ºdKH).'),
    ('Cardumen', 'Grupo de peces de la misma especie que nadan juntos; mantenerlos en número suficiente reduce su estrés.');


-- =====================================================================
-- 11. LÓGICA QUE SE PROGRAMA EN JAVA (ideas de ejercicios para los servicios)
-- =====================================================================
--  · Compatibilidad: guardar la pareja con el id menor en id_especie_a y
--    avisar si dos habitantes de un acuario son PRECAUCION o INCOMPATIBLE.
--  · Parámetros del acuario: el rango válido es el mayor de los mínimos y el
--    menor de los máximos de las especies que viven en él.
--  · Población: avisar si hay menos individuos que tamano_minimo_grupo o si
--    el acuario tiene menos litros que volumen_minimo_l.
--  · Tareas: al registrar un mantenimiento con id_tarea, calcular
--    proxima_fecha = fecha realizada + cada_n_dias.
--  · Imagen principal: al marcar una como principal, desmarcar las demás.
--  · Buscador: repositorio con findByNombreComunContainingIgnoreCase(...).
--  · Diagnóstico: contar cuántos síntomas marcados coinciden con cada enfermedad.
-- =====================================================================

-- Fin del script
