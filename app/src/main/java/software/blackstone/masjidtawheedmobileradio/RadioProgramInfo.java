package software.blackstone.masjidtawheedmobileradio;

/**
 * Created by ironmantis7x on 10/23/16.
 */


public class RadioProgramInfo
{
    private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+"]";
    }
}

