package br.com.srportto.models.enums;

public enum NivelPermissaoEnum {
    ROLE_ADMINISTRADOR(3L),
    ROLE_OPERADOR(2L),
    ROLE_CLIENTE(1L);

    private long nivelPermissao;

    NivelPermissaoEnum (long nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

    public long getNivelPermissao(){
        return this.nivelPermissao;
    }

    public static NivelPermissaoEnum obterNomePermissao(long nivelPermissao) {
        for (NivelPermissaoEnum permissao : NivelPermissaoEnum.values()) {
            if (permissao.getNivelPermissao() == nivelPermissao) {
                return permissao;
            }
        }
        throw new IllegalArgumentException(String.format("Nivel de permisão %i não conhecido ",nivelPermissao));
    }
}
