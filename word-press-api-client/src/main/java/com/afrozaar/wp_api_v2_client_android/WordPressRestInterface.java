package com.afrozaar.wp_api_v2_client_android;

import com.afrozaar.wp_api_v2_client_android.model.Comment;
import com.afrozaar.wp_api_v2_client_android.model.Media;
import com.afrozaar.wp_api_v2_client_android.model.Meta;
import com.afrozaar.wp_api_v2_client_android.model.Page;
import com.afrozaar.wp_api_v2_client_android.model.Post;
import com.afrozaar.wp_api_v2_client_android.model.Taxonomy;
import com.afrozaar.wp_api_v2_client_android.model.Type;
import com.afrozaar.wp_api_v2_client_android.model.User;
import com.afrozaar.wp_api_v2_client_android.model.dto.PostFeedResponseDto;
import com.afrozaar.wp_api_v2_client_android.model.dto.PostStreamItem;
import com.afrozaar.wp_api_v2_client_android.util.ContentUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * REST API interface for WP REST API plugin.
 *
 * @author Jan-Louis Crafford
 *         Created on 2016/01/12.
 */
public interface WordPressRestInterface {

    /* POSTS */

    /**
     * Creates a new Post.
     *
     * @param postFields Map of Post fields
     * @return The created Post object
     */
    @POST("posts")
    Call<Post> createPost(@Body Map<String, Object> postFields);

    /**
     * Gets all Posts.
     *
     * @return List of Post objects
     */
    @GET("posts")
    Call<List<Post>> getPosts();

    /**
     * Gets all Posts using provided query params
     *
     * @param map Optional query parameters
     * @return List of Post objects
     */
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> map);

    /**
     * Gets a single Post.
     *
     * @param postId Id of the Post
     * @param map    Optional query params
     * @return Post object
     */
    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") long postId, @QueryMap Map<String, String> map);

    /**
     * Gets all Posts created by a User.
     *
     * @param authorId Id of the User
     * @param status   The status of the post, eg. draft or publish
     * @return List of Post objects for the User
     */
    @GET("posts")
    Call<List<Post>> getPostsForAuthor(@Query("author") long authorId, @Query("status") String status, @Query("context") String context);

    /**
     * Updates an existing Post.
     *
     * @param postId     Id of the Post
     * @param postFields Map of the fields to update
     * @return The updated Post object
     */
    @POST("posts/{id}")
    Call<Post> updatePost(@Path("id") long postId, @Body Map<String, Object> postFields);

    /**
     * Marks a Post as Trash.
     *
     * @param postId Id of the Post
     * @return Post object that was deleted
     */
    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") long postId);

    /**
     * Deletes a Post.
     *
     * @param postId Id of the Post
     * @return Post object that was deleted
     */
    @DELETE("posts/{id}?force")
    Call<Post> deletePostForce(@Path("id") long postId);

    /**
     * Creates new Meta objects for a Post
     *
     * @param postId Id of the Post
     * @param fields Map containing key/value pairs
     * @return The created PostMeta object
     */
    @POST("posts/{id}/meta")
    Call<Meta> createPostMeta(@Path("id") long postId, @Body Map<String, Object> fields);

    @GET("posts/{id}/meta")
    Call<List<Meta>> getPostMeta(@Path("id") long postId);

    @GET("posts/{postId}/meta/{metaId}")
    Call<Meta> getPostMeta(@Path("postId") long postId, @Path("metaId") long metaId);

    @POST("posts/{postId}/meta/{metaId}")
    Call<Meta> updatePostMeta(@Path("postId") long postId, @Path("metaId") long metaId, @Body Map<String, Object> fields);

    @DELETE("posts/{postId}/meta/{metaId}?force=true")
    Call<Meta> deletePostMeta(@Path("postId") long postId, @Path("metaId") long metaId);


    @GET("posts/{postId}/revisions")
    Call<List<Post>> getPostRevisions(@Path("postId") long postId);

    @GET("posts/{postId}/revisions/{revId}")
    Call<Post> getPostRevision(@Path("postId") long postId, @Path("revId") long revId);

    @DELETE("posts/{postId}/revisions/{revId}")
    Call<Post> deltePostRevision(@Path("postId") long postId, @Path("revId") long revId);


    @POST("posts/{postId}/categories/{catId}")
    Call<Taxonomy> setPostCategory(@Path("postId") long postId, @Path("catId") long catId);

    @GET("posts/{postId}/categories")
    Call<List<Taxonomy>> getPostCategories(@Path("postId") long postId);

    @GET("posts/{postId}/categories/{catId}")
    Call<Taxonomy> getPostCategory(@Path("postId") long postId, @Path("catId") long catId);

    @DELETE("posts/{postId}/categories/{catId}")
    Call<Taxonomy> deletePostCategory(@Path("postId") long postId, @Path("catId") long catId);


    @POST("posts/{postId}/tags/{tagId}")
    Call<Taxonomy> setPostTag(@Path("postId") long postId, @Path("tagId") long tagId);

    //@GET("posts/{postId}/tags")
    //Call<List<Taxonomy>> getPostTags(@Path("postId") long postId);

    @GET("tags")
    Call<List<Taxonomy>> getPostTags(@Query("post") long postId);

    @GET("posts/{postId}/tags/{tagId}")
    Call<Taxonomy> getPostTag(@Path("postId") long postId, @Path("tagId") long catId);

    @DELETE("posts/{postId}/tags/{tagId}")
    Call<Taxonomy> deletePostTag(@Path("postId") long postId, @Path("tagId") long catId);


    /* CUSTOM POSTS */

    /**
     * Gets all Custom Posts.
     *
     * @param type of the Custom Post
     * @return List of Post objects
     */
    @GET("{type}")
    Call<List<Post>> getCustomPosts(@Path("type") String type);

    /**
     * Gets all Custom Posts using provided query params
     *
     * @param type of the Custom Post
     * @param map Optional query parameters
     * @return List of Post objects
     */
    @GET("{type}")
    Call<List<Post>> getCustomPosts(@Path("type") String type, @QueryMap Map<String, String> map);

    /**
     * Gets a single Custom Post.
     *
     * @param type of the Custom Post
     * @param postId Id of the Post
     * @param map    Optional query params
     * @return Post object
     */
    @GET("{type}/{id}")
    Call<Post> getCustomPost(@Path("type") String type, @Path("id") long postId, @QueryMap Map<String, String> map);


    /* PAGES */

    @POST("pages")
    Call<Page> createPage(@Body Map<String, Object> fieldMap);

    @GET("pages")
    Call<List<Page>> getPages();


    @GET("pages/{pageId}")
    Call<Page> getPage(@Path("pageId") long pageId);

    @POST("pages/{pageId}")
    Call<Page> updatePage(@Path("pageId") long pageId, @Body Map<String, Object> fieldMap);

    @DELETE("pages/{pageId}")
    Call<Page> deletePage(@Path("pageId") long pageId);


    @POST("pages/{pageId}/meta")
    Call<Meta> createPageMeta(@Path("pageId") long pageId, @Body Map<String, Object> fields);

    @GET("pages/{pageId}/meta")
    Call<List<Media>> getPageMeta(@Path("pageId") long pageId);

    @GET("pages/{pageId}/meta/{metaId}")
    Call<Meta> getPageMeta(@Path("pageId") long postId, @Path("metaId") long metaId);

    @POST("pages/{pageId}/meta/{metaId}")
    Call<Meta> updatePageMeta(@Path("pageId") long postId, @Path("metaId") long metaId, @Body Map<String, Object> fields);

    @DELETE("pages/{pageId}/meta/{metaId}")
    Call<Meta> deletePageMeta(@Path("pageId") long postId, @Path("metaId") long metaId);


    @GET("pages/{pageId}/revisions")
    Call<List<Page>> getPageRevisions(@Path("pageId") long postId);

    @GET("pages/{pageId}/revisions/{revId}")
    Call<Page> getPageRevision(@Path("pageId") long postId, @Path("revId") long revId);

    @DELETE("pages/{pageId}/revisions/{revId}")
    Call<Page> deltePageRevision(@Path("pageId") long postId, @Path("revId") long revId);


    /* MEDIA */

    /**
     * Upload a new Media item into WordPress.
     *
     * @param header Content-Disposition header containing filename, eg "filename=file.jpg"
     * @param params Map containing all fields to upload
     * @return Media item created
     * @see ContentUtil#makeMediaItemUploadMap(Media, File)
     */
    @Multipart
    @POST("media")
    Call<Media> createMedia(@Header("Content-Disposition") String header, @PartMap Map<String, RequestBody> params);

    /**
     * Gets all Media objects.
     *
     * @return List of Media objects
     */
    @GET("media")
    Call<List<Media>> getMedia();

    /**
     * Returns a single Media item.
     *
     * @param mediaId Id of the Media item
     * @return The Media object
     */
    @GET("media/{id}")
    Call<Media> getMedia(@Path("id") long mediaId);

    /**
     * Returns all Media items attached to a Post.
     *
     * @param postId Id of the Post
     * @param type   MIME type of Media
     * @return List of Media objects
     */
    @GET("posts/{id}/media/{type}")
    Call<List<Media>> getMediaForPost(@Path("id") long postId, @Path("type") String type);


    @GET("media")
    Call<List<Media>> getMediaForSlug(@QueryMap Map<String, Object> map);


    /**
     * Updates a Media item.
     *
     * @param mediaId Id the Media item
     * @param fields  Fields to update
     * @return The updated Media object
     */
    @POST("media/{id}")
    Call<Media> updateMedia(@Path("id") long mediaId, @Body Map<String, Object> fields);

    /**
     * Deletes a Media item.
     *
     * @param mediaId Id of the Media item
     * @return The deleted Media object
     */
    @DELETE("media/{id}?force=true")
    Call<Media> deleteMedia(@Path("id") long mediaId);


    /* TYPES */

    @GET("types")
    Call<List<Type>> getTypes();


    /* STATUSES */

    // @GET("statuses")

    // @GET("statuses/{statusId}")


    /* TAXONOMIES */

    // @GET("taxonomies")

    // @GET("taxonomies/{id}")


    /* CATEGORIES */

    @POST("categories")
    Call<Taxonomy> createCategory(@Body Map<String, Object> fields);

    @GET("categories")
    Call<List<Taxonomy>> getCategories();

    @GET("categories/{id}")
    Call<Taxonomy> getCategory(@Path("id") long id);

    @GET("categories")
    Call<List<Taxonomy>> getCategoryForSlug(@QueryMap Map<String, String> map);

    @GET("categories")
    Call<List<Taxonomy>> getCategories(@QueryMap Map<String, Object> map);

    @POST("categories/{id}")
    Call<Taxonomy> updateCategory(@Path("id") long id, Map<String, Object> fields);

    @DELETE("categories/{id}")
    Call<Taxonomy> deleteCategory(@Path("id") long id);


    /* TAGS */

    @POST("tags")
    Call<Taxonomy> createTag(@Body Map<String, Object> fields);

    @GET("tags")
    Call<List<Taxonomy>> getTags();

    @GET("tags")
    Call<List<Taxonomy>> getTagsOrdered(@QueryMap Map<String, String> map);

    @GET("tags")
    Call<List<Taxonomy>> getTagForSlug(@QueryMap Map<String, String> map);

    @GET("tags/{id}")
    Call<Taxonomy> getTag(@Path("id") long id);

    @POST("tags/{id}")
    Call<Taxonomy> updateTag(@Path("id") long id, Map<String, Object> fields);

    @DELETE("tags/{id}")
    Call<Taxonomy> deleteTag(@Path("id") long id);


    /* USERS */

    /**
     * Creates a new WordPress user.
     *
     * @param fields Map of fields
     * @return The created User object
     */
    @POST("users")
    Call<User> createUser(@Body Map<String, Object> fields);

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") long id);

    @POST("users/{id}")
    Call<User> updateUser(@Path("id") long id, @Body Map<String, Object> fields);

    @DELETE("users/{id}")
    Call<User> deleteUser(@Path("id") long id);

    /**
     * Gets existing User using username.
     *
     * @param username Login username of the User
     * @return The User object
     */
    @GET("users/login/{username}")
    Call<User> getUserFromLogin(@Path("username") String username);

    @GET("users/email/{email}")
    Call<User> getUserFromEmail(@Path("email") String email);

    @GET("users/me")
    Call<User> getUserMe();


    /* COMMENTS */

    @POST("comments")
    Call<Comment> createComment(@Body Map<String, Object> fields);

    @GET("comments?context=edit")
    Call<List<Comment>> getComments(@QueryMap Map<String, Object> map);

    @GET("comments/{id}")
    Call<Comment> getComment(@Path("id") long id);

    @GET("comments")
    Call<List<Comment>> getCommentsForPost(@QueryMap Map<String, Object> map);

    @POST("comments/{id}")
    Call<Comment> updateComment(@Path("id") long id, Map<String, Object> fields);

    @DELETE("comments/{id}")
    Call<Comment> deleteComment(@Path("id") long id);

    
    /* OTHER */

    @GET("posts")
    Call<List<Post>> getPostsForTags(@Query("filter[tag]") String tag);

    @GET("posts/stream")
    Call<List<PostStreamItem>> getPostStream();

    @GET("posts/stream/{date}")
    Call<List<PostStreamItem>> getPostStreamAfterDate(@Path("date") String date);

    @GET("posts/stream")
    Call<List<PostStreamItem>> getPostFeedForPage(@Query("page") int page);
}
