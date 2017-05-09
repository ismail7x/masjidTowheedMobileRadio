package software.blackstone.masjidtawheedmobileradio;

/**
 * Created by ironmantis7x on 10/23/16.
 */

public class RadioItems
{
    public String duration;
    public String title;
    public String download_enabled;
    public String image_original_url;
    public String image_url;
    public String explicit;
    public String episode_id;
    public String author_id;
    public String show_id;
    public String type;
    public String waveform_url;
    public String published_at;
    public String site_url;
    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDownload_enabled ()
    {
        return download_enabled;
    }

    public void setDownload_enabled (String download_enabled)
    {
        this.download_enabled = download_enabled;
    }

    public String getImage_original_url ()
    {
        return image_original_url;
    }

    public void setImage_original_url (String image_original_url)
    {
        this.image_original_url = image_original_url;
    }

    public String getImage_url ()
    {
        return image_url;
    }

    public void setImage_url (String image_url)
    {
        this.image_url = image_url;
    }

    public String getExplicit ()
    {
        return explicit;
    }

    public void setExplicit (String explicit)
    {
        this.explicit = explicit;
    }

    public String getEpisode_id ()
    {
        return episode_id;
    }

    public void setEpisode_id (String episode_id)
    {
        this.episode_id = episode_id;
    }

    public String getAuthor_id ()
    {
        return author_id;
    }

    public void setAuthor_id (String author_id)
    {
        this.author_id = author_id;
    }

    public String getShow_id ()
    {
        return show_id;
    }

    public void setShow_id (String show_id)
    {
        this.show_id = show_id;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getWaveform_url ()
    {
        return waveform_url;
    }

    public void setWaveform_url (String waveform_url)
    {
        this.waveform_url = waveform_url;
    }

    public String getPublished_at ()
    {
        return published_at;
    }

    public void setPublished_at (String published_at)
    {
        this.published_at = published_at;
    }

    public String getSite_url ()
    {
        return site_url;
    }

    public void setSite_url (String site_url)
    {
        this.site_url = site_url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", title = "+title+", download_enabled = "+download_enabled+", image_original_url = "+image_original_url+", image_url = "+image_url+", explicit = "+explicit+", episode_id = "+episode_id+", author_id = "+author_id+", show_id = "+show_id+", type = "+type+", waveform_url = "+waveform_url+", published_at = "+published_at+", site_url = "+site_url+"]";
    }
}

