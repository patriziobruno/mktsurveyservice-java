/* 
 * Copyright 2016 eul0860.
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
package net.dstc.mkts.api;

import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.inject.Inject;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyDAO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Resource
@ManagedBean
public class MarketingSurveyApiImpl implements MarketingSurveyApi {

    @Inject
    private SurveyDAO data;

    private DataConverters.Converter converter;

    private DataConverters.Converter getConverter() {
        if (converter == null) {
            converter = DataConverters.getConverter(data);
        }
        return converter;
    }

    @Override
    public Collection<SurveyDTO> getList(SurveyDTO query) {
        SurveyDO daoQuery = null;

        if (query != null) {
            daoQuery = getConverter().surveyDTOtoDO(query);
        }

        return data.list(daoQuery).stream().map(s -> getConverter().surveyDOtoDTO(s))
                .collect(Collectors.toList());
    }

    @Override
    public SurveyDTO insert(SurveyDTO survey) {
        SurveyDO s = getConverter().surveyDTOtoDO(survey);
        data.add(s);
        return getConverter().surveyDOtoDTO(s);
    }

    @Override
    public void update(SurveyDTO survey) {
        SurveyDO s = getConverter().surveyDTOtoDO(survey);
        data.update(s);
    }

    @Override
    public void delete(String id) {
        if(StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("id must have a value");
        }
        data.delete(id);
    }

    @Override
    public SurveyDTO get(String id) {
        if(StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("id must have a value");
        }
        return getConverter().surveyDOtoDTO(data.get(id));
    }
}
