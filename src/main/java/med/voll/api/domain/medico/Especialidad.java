package med.voll.api.domain.medico;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum Especialidad {
    @JsonAlias ("ortopedia") ORTOPEDIA,
    @JsonAlias ("cardiologia") CARDIOLOGIA,
    @JsonAlias ("ginecologia") GINECOLOGIA,
    @JsonAlias ("dermatologia") DERMATOLOGIA
}
