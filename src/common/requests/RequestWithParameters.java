package common.requests;

import common.exceptions.WrongParameterException;

public interface RequestWithParameters {
    void setParameters(String... parameters) throws WrongParameterException;
}
