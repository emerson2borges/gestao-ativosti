package com.ativosti.util;

import com.ativosti.exception.ResourceNotFoundException;
import com.ativosti.exception.BusinessException;
import com.ativosti.exception.ValidationException;

import java.util.List;
import java.util.Arrays;

public class MessageUtils {

    // Mesagens de erro padronizadas
    public static final String MSG_NAO_ENCONTRADO = "{0} não encontrado com id: {1}";
    public static final String MSG_JA_EXISTE = "Já existe um(a) {0} com {1}: {2}";
    public static final String MSG_STATUS_INVALIDO = "Status inválido. Valores permitidos: {0}";
    public static final String MSG_ESTOQUE_INVALIDO = "Estoque inválido: {0} {1}";

    // Método para formatar e lançar exceções de recurso:
    // a. não encontrado
    // b. já existe
    // c. status inválido
    // Exemplo de uso: throw MessageUtils.notFound("Ativo", id);
    public static ResourceNotFoundException notFound(String recurso, Long id) {
        String msg = format(MSG_NAO_ENCONTRADO, recurso, id);
        return new ResourceNotFoundException(msg);
    }

    public static BusinessException alreadyExists(String recurso, String campo, String valor) {
        String msg = format(MSG_JA_EXISTE, recurso, campo, valor);
        return new BusinessException(msg);
    }

    public static ValidationException invalidStatus(String status, List<String> validos) {
        String msg = format(MSG_STATUS_INVALIDO, validos);
        return new ValidationException(msg);
    }

    public static ValidationException invalidStock(String campo, String motivo) {
        String msg = "Estoque inválido: " + campo + " " + motivo;
        return new ValidationException(msg);
    }

    public static ValidationException invalidStock(String campo, String motivo) {
        String msg = format(MSG_ESTOQUE_INVALIDO, campo, motivo);
        return new ValidationException(msg);
    }

    // Método auxiliar para substituir placeholders {0}, {1}, ...
    private static String format(String template, Object... args) {
        String msg = template;
        for (int i = 0; i < args.length; i++) {
            msg = msg.replace("{" + i + "}", String.valueOf(args[i]));
        }
        return msg;
    }
}