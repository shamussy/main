package owlmoney.logic.parser.bond;

import java.util.Date;
import java.util.Iterator;

import owlmoney.logic.command.Command;
import owlmoney.logic.command.bond.AddBondCommand;
import owlmoney.logic.parser.exception.ParserException;

/**
 * Parses input by user for adding bonds.
 */
public class ParseAddBond extends ParseBond {
    private static final String ADD_COMMAND = "/add";
    private Date date;

    /**
     * Creates an instance of ParseAddBond.
     *
     * @param data Raw data of user input to be parsed.
     * @throws ParserException If there is a redundant parameter or first parameter is not a valid type.
     */
    public ParseAddBond(String data, String type) throws ParserException {
        super(data, type);
        checkRedundantParameter(NUM_PARAMETER, ADD_COMMAND);
        checkFirstParameter();
    }

    /**
     * Checks each user input for each parameter.
     *
     * @throws ParserException If there are any invalid or missing input.
     */
    public void checkParameter() throws ParserException {
        Iterator<String> bondIterator = bondParameters.keySet().iterator();

        while (bondIterator.hasNext()) {
            String key = bondIterator.next();
            String value = bondParameters.get(key);
            if (!NUM_PARAMETER.equals(key) && (value == null || value.isBlank())) {
                logger.warning(key + " cannot be empty when adding bond");
                throw new ParserException(key + " cannot be empty when adding bond");
            }
            if (NAME_PARAMETER.equals(key)) {
                checkName(NAME_PARAMETER, value);
            }
            if (FROM_PARAMETER.equals(key)) {
                checkName(FROM_PARAMETER, value);
            }
            if (AMOUNT_PARAMETER.equals(key)) {
                checkAmount(value);
            }
            if (RATE_PARAMETER.equals(key)) {
                checkInterestRate(value);
            }
            if (DATE_PARAMETER.equals(key)) {
                date = checkDate(value);
            }
            if (YEAR_PARAMETER.equals(key)) {
                checkYear(value);
            }
        }
    }

    /**
     * Returns the command to execute the adding of a new bond.
     *
     * @return AddBondCommand to be executed.
     */
    public Command getCommand() {
        AddBondCommand newAddBondCommand = new AddBondCommand(bondParameters.get(NAME_PARAMETER),
                bondParameters.get(FROM_PARAMETER),
                Double.parseDouble(bondParameters.get(AMOUNT_PARAMETER)),
                Double.parseDouble(bondParameters.get(RATE_PARAMETER)),
                this.date,
                Integer.parseInt(bondParameters.get(YEAR_PARAMETER)), this.type);
        logger.info("Successful creation of AddBondCommand object");
        return newAddBondCommand;
    }
}
