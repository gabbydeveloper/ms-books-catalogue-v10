package com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.specification;

import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Categoria;
import com.msbookscataloguev10.com.co.msbookscataloguev10.persistencia.entity.Libro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LibroSpecifications {

    // Filtro por titulo (LIKE)
    public static Specification<Libro> tituloContiene(String titulo) {
        return (root, query, cb) -> {
            if (titulo == null || titulo.trim().isEmpty()) return cb.conjunction();
            return cb.like(cb.upper(root.get("tituloLibro")), "%" + titulo.trim().toUpperCase() + "%");
        };
    }

    // Filtro por autor (idAutor) -> NO depende de nombres del autor
    public static Specification<Libro> autorIdEs(Long idAutor) {
        return (root, query, cb) -> {
            if (idAutor == null) return cb.conjunction();
            // Libro tiene ManyToOne Autor autor [2](https://saludcapitalgovco-my.sharepoint.com/personal/dgpaez_saludcapital_gov_co/Documents/Archivos%20de%20Microsoft%C2%A0Copilot%20Chat/MensajesConstantes.java)
            return cb.equal(root.get("autor").get("idAutor"), idAutor);
        };
    }

    // Filtro por categoria (idCategoria) -> join ManyToMany categorias [2](https://saludcapitalgovco-my.sharepoint.com/personal/dgpaez_saludcapital_gov_co/Documents/Archivos%20de%20Microsoft%C2%A0Copilot%20Chat/MensajesConstantes.java)
    public static Specification<Libro> categoriaIdEs(Long idCategoria) {
        return (root, query, cb) -> {
            if (idCategoria == null) return cb.conjunction();
            Join<Libro, Categoria> joinCat = root.join("categorias", JoinType.INNER);
            query.distinct(true); // evita duplicados por ManyToMany
            return cb.equal(joinCat.get("idCategoria"), idCategoria);
        };
    }

    // Filtro por precio mínimo
    public static Specification<Libro> precioMayorOIgual(Double minPrecio) {
        return (root, query, cb) -> {
            if (minPrecio == null) return cb.conjunction();
            return cb.greaterThanOrEqualTo(root.get("precioLibro"), minPrecio);
        };
    }

    // Filtro por precio máximo
    public static Specification<Libro> precioMenorOIgual(Double maxPrecio) {
        return (root, query, cb) -> {
            if (maxPrecio == null) return cb.conjunction();
            return cb.lessThanOrEqualTo(root.get("precioLibro"), maxPrecio);
        };
    }

    // Keyword simple (opcional) sobre campos del libro (sin autor para evitar suposiciones)
    public static Specification<Libro> keywordEnLibro(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return cb.conjunction();
            String k = "%" + keyword.trim().toUpperCase() + "%";
            return cb.or(
                    cb.like(cb.upper(root.get("tituloLibro")), k),
                    cb.like(cb.upper(root.get("sinopsisLibro")), k),
                    cb.like(cb.upper(root.get("codigoIsbnLibro")), k)
            );
        };
    }
    public static Specification<Libro> categoriaNombreContiene(String nombreCategoria) {
        return (root, query, cb) -> {
            if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) return cb.conjunction();

            Join<Libro, Categoria> joinCat = root.join("categorias", JoinType.INNER);
            query.distinct(true);

            return cb.like(
                    cb.upper(joinCat.get("nombreCategoria")),
                    "%" + nombreCategoria.trim().toUpperCase() + "%"
            );
        };
    }
    public static Specification<Libro> autorNombresContiene(String nombresAutor) {
        return (root, query, cb) -> {
            if (nombresAutor == null || nombresAutor.trim().isEmpty()) return cb.conjunction();
            var autorJoin = root.join("autor", JoinType.INNER);
            return cb.like(
                    cb.upper(autorJoin.get("nombresAutor")),
                    "%" + nombresAutor.trim().toUpperCase() + "%"
            );
        };
    }

    public static Specification<Libro> autorPrimerApellidoContiene(String primerApellidoAutor) {
        return (root, query, cb) -> {
            if (primerApellidoAutor == null || primerApellidoAutor.trim().isEmpty()) return cb.conjunction();
            var autorJoin = root.join("autor", JoinType.INNER);
            return cb.like(
                    cb.upper(autorJoin.get("primerApellidoAutor")),
                    "%" + primerApellidoAutor.trim().toUpperCase() + "%"
            );
        };
    }

    public static Specification<Libro> autorSegundoApellidoContiene(String segundoApellidoAutor) {
        return (root, query, cb) -> {
            if (segundoApellidoAutor == null || segundoApellidoAutor.trim().isEmpty()) return cb.conjunction();
            var autorJoin = root.join("autor", JoinType.INNER);
            return cb.like(
                    cb.upper(autorJoin.get("segundoApellidoAutor")),
                    "%" + segundoApellidoAutor.trim().toUpperCase() + "%"
            );
        };
    }
    // Filtro por sinopsis (LIKE)
    public static Specification<Libro> sinopsisContiene(String sinopsis) {
        return (root, query, cb) -> {
            if (sinopsis == null || sinopsis.trim().isEmpty()) return cb.conjunction();
            return cb.like(cb.upper(root.get("sinopsisLibro")), "%" + sinopsis.trim().toUpperCase() + "%");
        };
    }

    // Filtro por ISBN (LIKE o exacto, tú decides; aquí LIKE)
    public static Specification<Libro> isbnContiene(String isbn) {
        return (root, query, cb) -> {
            if (isbn == null || isbn.trim().isEmpty()) return cb.conjunction();
            return cb.like(cb.upper(root.get("codigoIsbnLibro")), "%" + isbn.trim().toUpperCase() + "%");
        };
    }

    // Filtro por formato (EQUAL)
    public static Specification<Libro> formatoEs(String formato) {
        return (root, query, cb) -> {
            if (formato == null || formato.trim().isEmpty()) return cb.conjunction();
            return cb.equal(cb.upper(root.get("formatoLibro")), formato.trim().toUpperCase());
        };
    }

    // Filtro por estado (EQUAL)
    public static Specification<Libro> estadoEs(String estado) {
        return (root, query, cb) -> {
            if (estado == null || estado.trim().isEmpty()) return cb.conjunction();
            return cb.equal(cb.upper(root.get("estadoLibro")), estado.trim().toUpperCase());
        };
    }

    // Filtro por fechaPublicacion (EQUAL)
// (OJO: en tu entity es String, no Date)
    public static Specification<Libro> fechaPublicacionEs(String fecha) {
        return (root, query, cb) -> {
            if (fecha == null || fecha.trim().isEmpty()) return cb.conjunction();
            return cb.equal(root.get("fechaPublicacionLibro"), fecha.trim());
        };
    }
}