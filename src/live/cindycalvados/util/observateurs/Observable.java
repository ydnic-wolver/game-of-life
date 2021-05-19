/**
 * @author CALVADOS Cindy
 */


package live.cindycalvados.util.observateurs;

/**
 * Interface Observable
 */
public interface Observable {

    /**
     * Attache un observateur
     * @param o  Observateur à attacher
     */
    void attacheObservateur(Observateur o);

    /**
     * Detache un observateur
     * @param o  Observateur à attacher
     */
    void detacheObservateur(Observateur o);

    /**
     * Notifie les observateurs
     */
    void notifieObservateur();
}
