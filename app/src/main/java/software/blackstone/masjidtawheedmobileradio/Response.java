
package software.blackstone.masjidtawheedmobileradio;

public class Response
{
    private RadioItems[] items;

    private String next_url;

    public RadioItems[] getItems ()
    {
        return items;
    }

    public void setItems (RadioItems[] items)
    {
        this.items = items;
    }

    public String getNext_url ()
    {
        return next_url;
    }

    public void setNext_url (String next_url)
    {
        this.next_url = next_url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [items = "+items+", next_url = "+next_url+"]";
    }
}


