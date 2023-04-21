package mx.edu.utez.Examen.Recu3.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomResponse<T>{
    T data;
    boolean error;
    int statusCode;
    String message;
}
