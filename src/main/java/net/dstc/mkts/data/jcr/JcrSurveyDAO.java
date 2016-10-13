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
package net.dstc.mkts.data.jcr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import net.dstc.mkts.data.SurveyStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.core.RepositoryImpl;
import org.apache.jackrabbit.core.config.RepositoryConfig;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;
import org.apache.jackrabbit.ocm.mapper.Mapper;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import org.apache.jackrabbit.ocm.query.Filter;
import org.apache.jackrabbit.ocm.query.Query;
import org.apache.jackrabbit.ocm.query.QueryManager;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyTargetDO;
import net.dstc.mkts.data.SurveyDAO;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Resource

@Node
@Singleton
public class JcrSurveyDAO implements SurveyDAO {

    private static ObjectContentManager ocm = null;

    static {
        staticInit();
    }

    static void staticInit() {
        try {
            RepositoryConfig config = RepositoryConfig.create(SurveyDAO.class.
                    getClassLoader().getResourceAsStream("repository.xml"),
                    "/tmp/mktsurvey");
            Repository rep = RepositoryImpl.create(config);
            Session session = rep.login(new SimpleCredentials("admin", "admin".
                    toCharArray()));

            List<Class> classes = new ArrayList<>(1);
            classes.add(SurveyTargetDO.class);
            classes.add(JcrSurveyTargetDO.class);
            classes.add(JcrSurveyDO.class);
            classes.add(JcrSurveyDAO.class);
            Mapper mapper = new AnnotationMapperImpl(classes);

            session.getRootNode().addNode("survey");
            session.save();

            ocm = new ObjectContentManagerImpl(session, mapper);
        } catch (RepositoryException ex) {
            Logger.getLogger(JcrSurveyDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    private Query createSearchQuery(SurveyDO query) {
        QueryManager qm = ocm.getQueryManager();

        Filter filter = qm.createFilter(JcrSurveyDO.class);
        filter.setScope("/survey//");

        if (query != null) {
            String id = query.getId();
            if (!StringUtils.isEmpty(id)) {
                filter.addEqualTo("id", id);
            }

            Date startDate = query.getStartDate();
            if (startDate != null) {
                filter.addEqualTo("startDate", startDate);
            }

            SurveyStatus status = query.getStatus();
            if (status != null) {
                filter.addEqualTo("status", status);
            }

            String title = query.getTitle();
            if (!StringUtils.isEmpty(title)) {
                filter.addEqualTo("title", title);
            }

            SurveyTargetDO target = query.getTarget();
            if (target != null) {
                String targetExpression = createTargetFilter(target).toString();
                if (!StringUtils.isEmpty(targetExpression)) {
                    filter.addJCRExpression(targetExpression.replaceAll(
                            "@([a-z]+)", ".//target/@$1"));
                }
            }
        }

        Query searchQuery = qm.createQuery(filter);

        return searchQuery;
    }

    private Filter createTargetFilter(SurveyTargetDO query) {
        QueryManager qm = ocm.getQueryManager();
        Filter filter = qm.createFilter(JcrSurveyTargetDO.class);

        String country = query.getCountry();
        if (!StringUtils.isEmpty(country)) {
            filter.addEqualTo("country", country);
        }

        String gender = query.getGender();
        if (!StringUtils.isEmpty(gender)) {
            filter.addEqualTo("gender", gender);
        }

        int maxAge = query.getMaxAge();
        if (maxAge != 0) {
            filter.addLessOrEqualThan("maxAge", maxAge);
            filter.addLessThan("minAge", maxAge);
        }

        int minAge = query.getMinAge();
        if (minAge != 0) {
            filter.addGreaterOrEqualThan("minAge", minAge);
            filter.addGreaterThan("maxAge", minAge);
        }

        int maxIncome = query.getMaxIncome();
        if (maxIncome != 0) {
            filter.addLessOrEqualThan("maxIncome", maxIncome);
            filter.addLessThan("minIncome", maxIncome);
        }

        int minIncome = query.getMinIncome();
        if (minIncome != 0) {
            filter.addGreaterOrEqualThan("minIncome", minIncome);
            filter.addGreaterThan("maxIncome", minIncome);
        }

        return filter;
    }

    @Override
    public void add(SurveyDO survey) {
        ocm.insert(survey);
        ocm.save();
    }

    @Override
    public Collection<SurveyDO> list(SurveyDO query) {
        Query searchQuery = createSearchQuery(query);
        return ocm.getObjects(searchQuery);
    }

    @Override
    public SurveyDO get(String id) {
        return (SurveyDO) ocm.getObject(JcrSurveyDO.class, id);
    }

    @Override
    public void delete(String id) {
        ocm.remove(id);
        ocm.save();
    }

    @Override
    public void update(SurveyDO s) {
        ocm.update(s);
        ocm.save();
    }

    @Override
    public SurveyDO createSurvey() {
        return new JcrSurveyDO();
    }

    @Override
    public SurveyTargetDO createSurveyTarget() {
        return new JcrSurveyTargetDO();
    }
}
