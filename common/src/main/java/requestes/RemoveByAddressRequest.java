package requestes;

import abstractions.creator.Creator;
import model.Address;

public class RemoveByAddressRequest extends CreationCommandRequest<Address> {
    public RemoveByAddressRequest(Creator<Address> creator) {
        super("remove_any_by_official_address", creator);
    }

    @Override
    public void setCommandArgs(String[] args) {
        checkArgumentsAmount(args, 0);
        createArgument();
    }
}
