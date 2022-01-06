/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package genius;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DeezerClient {

    public static final String PREFIX_ALBUM = "album";
    public static final String PREFIX_ARTIST = "artist";
    public static final String PREFIX_USER = "user";
    public static final String PREFIX_PLAYLIST = "playlist";
    public static final String PREFIX_TRACK = "track";

    private DeezerRestTemplate baseService;

    public DeezerClient(ResourceConnection resourceConnection) {
        baseService = new DeezerRestTemplate(resourceConnection);
    }

    public Artist get(final ArtistId artistId) {
        return baseService.get(PREFIX_ARTIST, artistId.getId(), Artist.class);
    }

    public Genres getGenres() {
        return baseService.get("genre", Genres.class);
    }

    public Track get(final TrackId trackId) {
        return baseService.get(PREFIX_TRACK, trackId.getId(), Track.class);
    }

    private String getSearchQuery(final Search search) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("q=");
        try {
            queryBuilder.append(URLEncoder.encode(search.getText(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (search.getSearchOrder() != null) {
            queryBuilder.append("&order=");
            queryBuilder.append(search.getSearchOrder());
        }
        return queryBuilder.toString();
    }

}