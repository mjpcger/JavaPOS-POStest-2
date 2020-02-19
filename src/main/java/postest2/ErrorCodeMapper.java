package postest2;

/**
 * Class used to map error codes to strings
 */
public class ErrorCodeMapper {
    protected Object[] Mappings;
    /**
     * Retrieves symbolic name of an error code. In no valid error code
     * can be found, a string value that contains the code will be returned.
     * @param code Error code to be translated.
     * @return Symbolic error code name.
     */
    public String getName(int code) {
        for (int i = 0; i < Mappings.length; i += 2) {
            if ((Integer)(Mappings[i]) == code) {
                return (String)(Mappings[i + 1]);
            }
        }
        return Integer.toString(code);
    }
}
