package com.afrozaar.wp_api_v2_client_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.afrozaar.wp_api_v2_client_android.util.Validate;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/07.
 */
public class Media extends WPObject<Media> {

    public static final String JSON_FIELD_ALT_TEXT = "alt_text";
    public static final String JSON_FIELD_CAPTION = "caption";
    public static final String JSON_FIELD_DESCRIPTION = "description";
    public static final String JSON_FIELD_MEDIA_TYPE = "media_type";
    public static final String JSON_FIELD_MIME_TYPE = "mime_type";
    public static final String JSON_FIELD_POST = "post";
    public static final String JSON_FIELD_SOURCE_URL = "source_url";
    public static final String JSON_FIELD_MEDIA_DETAILS = "media_details";

    // custom upload states
    public static final int UPLOAD_STATE_NONE = 0;  // not uploaded yet
    public static final int UPLOAD_STATE_UPLOADED = 1;  // uploaded
    public static final int UPLOAD_STATE_ERROR = 2;     // error on upload

    // custom fields for local app use
    public long origId;
    public String origType;
    public String origUri;
    public int uploadState;


    /**
     * Alternative text to display when attachment is not displayed.
     */
    @SerializedName("alt_text")
    private String altText;

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getAltText() {
        return altText;
    }

    public Media withAltText(String altText) {
        setAltText(altText);
        return this;
    }

    /**
     * The caption for the attachment.
     */
    @SerializedName("caption")
    private WPGeneric caption;

    public void setCaption(WPGeneric caption) {
        this.caption = caption;
    }

    public WPGeneric getCaption() {
        return caption;
    }

    public Media withCaption(String caption) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(caption);
        setCaption(generic);
        return this;
    }

    /**
     * The description for the attachment.
     */
    @SerializedName("description")
    private WPGeneric description;

    public void setDescription(WPGeneric description) {
        this.description = description;
    }

    public WPGeneric getDescription() {
        return description;
    }

    public Media withDescription(String description) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(description);
        setDescription(generic);
        return this;
    }

    /**
     * Type of attachment.
     */
    @SerializedName("media_type")
    private String mediaType;

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Media withMediaType(String mediaType) {
        setMediaType(mediaType);
        return this;
    }

    /**
     * MIME type of the attachment object
     */
    @SerializedName(JSON_FIELD_MIME_TYPE)
    private String mimeType;

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Media withMimeType(String mimeType) {
        setMimeType(mimeType);
        return this;
    }

    /**
     * The ID for the associated post of the attachment.
     */
    @SerializedName("post")
    private long postId = -1;

    public void setPost(long postId) {
        this.postId = postId;
    }

    public long getPostId() {
        return postId;
    }

    public Media withPostId(long postId) {
        setPost(postId);
        return this;
    }

    /**
     * URL to the original attachment file.
     */
    @SerializedName("source_url")
    private String sourceUrl;

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Media withSourceUrl(String sourceUrl) {
        setSourceUrl(sourceUrl);
        return this;
    }

    /**
     * Details about the attachment file, specific to its type.
     */
    @SerializedName("media_details")
    private MediaDetails mediaDetails;

    public void setMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

    public MediaDetails getMediaDetails() {
        return mediaDetails;
    }

    public Media withMediaDetails(MediaDetails mediaDetails) {
        setMediaDetails(mediaDetails);
        return this;
    }

    @Override
    public Media withId(long id) {
        setId(id);
        return this;
    }

    @Override
    public Media withDate(String date) {
        setDate(date);
        return this;
    }

    @Override
    public Media withDateGMT(String dateGMT) {
        setDateGMT(dateGMT);
        return this;
    }

    @Override
    public Media withGuid(String guid) {
        WPGeneric generic = new WPGeneric();
        generic.setRaw(guid);
        setGuid(generic);
        return this;
    }

    @Override
    public Media withModified(String modified) {
        setModified(modified);
        return this;
    }

    @Override
    public Media withModifiedGMT(String modifiedGMT) {
        setModifiedGMT(modifiedGMT);
        return this;
    }

    @Override
    public Media withSlug(String slug) {
        setSlug(slug);
        return this;
    }

    @Override
    public Media withType(String type) {
        setType(type);
        return this;
    }

    @Override
    public Media withLink(String link) {
        setLink(link);
        return this;
    }

    @Override
    public Media withTitle(String title) {
        WPGeneric generic = new WPGeneric();
        generic.setRendered(title);
        setTitle(generic);
        return this;
    }

    @Override
    public Media withAuthor(int author) {
        setAuthor(author);
        return this;
    }

    @Override
    public Media withCommentStatus(int commentStatus) {
        WPStatus status = new WPStatus();
        status.setStatus(commentStatus);
        setCommentStatus(status);
        return this;
    }

    @Override
    public Media withPingStatus(int pingStatus) {
        WPStatus status = new WPStatus();
        status.setStatus(pingStatus);
        setCommentStatus(status);
        return this;
    }

    @Override
    public Media withLinks(ArrayList<Link> links) {
        return this;
    }

    @Override
    public Media withLink(Link link) {
        return this;
    }

    public Media() {
    }

    public Media(Parcel in) {
        super(in);
        altText = in.readString();
        caption = in.readParcelable(WPGeneric.class.getClassLoader());
        description = in.readParcelable(WPGeneric.class.getClassLoader());
        mediaType = in.readString();
        postId = in.readLong();
        sourceUrl = in.readString();
        mediaDetails = in.readParcelable(MediaDetails.class.getClassLoader());

        origId = in.readLong();
        origType = in.readString();
        origUri = in.readString();
        uploadState = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(altText);
        dest.writeParcelable(caption, flags);
        dest.writeParcelable(description, flags);
        dest.writeString(mediaType);
        dest.writeLong(postId);
        dest.writeString(sourceUrl);
        dest.writeParcelable(mediaDetails, flags);

        dest.writeLong(origId);
        dest.writeString(origType);
        dest.writeString(origUri);
        dest.writeInt(uploadState);
    }

    public static Map<String, Object> mapFromFields(Media media) {
        Map<String, Object> builder = new HashMap<>();

        WPObject.mapFromFields(media, builder);

        Validate.validateMapEntry(JSON_FIELD_ALT_TEXT, media.getAltText(), builder);
        Validate.validateMapEntry(JSON_FIELD_CAPTION, media.getCaption(), builder);
        Validate.validateMapEntry(JSON_FIELD_DESCRIPTION, media.getDescription(), builder);
        Validate.validateMapEntry(JSON_FIELD_MEDIA_TYPE, media.getMediaType(), builder);
        Validate.validateMapEntry(JSON_FIELD_POST, media.getPostId(), builder);
        Validate.validateMapEntry(JSON_FIELD_SOURCE_URL, media.getSourceUrl(), builder);
        Validate.validateMapEntry(JSON_FIELD_MEDIA_DETAILS, media.getMediaDetails(), builder);

        return builder;
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    public static final Parcelable.Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel source) {
            return new Media(source);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    @Override
    public String toString() {
        return "Media{" +
                "altText='" + altText + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", postId=" + postId +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", mediaDetails=" + mediaDetails +
                "} " + super.toString();
    }
}
