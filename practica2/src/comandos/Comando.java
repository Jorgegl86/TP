package comandos;

import general.Mundo;

public abstract class Comando{
  public abstract void ejecutar(Mundo mundo);
  public abstract Comando parsea(String[] cadenaComando);
  public abstract String textoAyuda();


}
