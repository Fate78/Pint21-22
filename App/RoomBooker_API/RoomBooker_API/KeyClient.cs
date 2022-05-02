using Azure.Identity;

internal class KeyClient
{
    private Uri uri;
    private DefaultAzureCredential defaultAzureCredential;

    public KeyClient(Uri uri, DefaultAzureCredential defaultAzureCredential)
    {
        this.uri = uri;
        this.defaultAzureCredential = defaultAzureCredential;
    }
}