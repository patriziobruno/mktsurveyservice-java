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
import net.dstc.mkts.data.SurveyTargetDO;
import net.dstc.mkts.data.SurveyDAO;
import net.dstc.mkts.data.SurveyStatus;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Resource
@ManagedBean
public class MarketingSurveyApiImpl implements MarketingSurveyApi {

    @Inject
    private SurveyDAO data;

    @Override
    public Collection<SurveyDTO> getList(SurveyDTO query) {
        SurveyDO daoQuery = null;

        if (query != null) {
            daoQuery = data.createSurvey();

            daoQuery.setId(query.getId());
            daoQuery.setStartDate(query.getStartDate());
            daoQuery.setStatus(query.getStatus());
            daoQuery.setTitle(query.getTitle());

            SurveyTargetDTO targetq = query.getTarget();
            if (targetq != null) {
                SurveyTargetDO target = data.createSurveyTarget();
                daoQuery.setTarget(target);
                target.setCountry(targetq.getCountry());
                target.setGender(targetq.getGender());
                int[] ageRange = targetq.getAgeRange();
                if (ageRange != null && ageRange.length > 1) {
                    target.setMinAge(ageRange[0]);
                    target.setMaxAge(ageRange[1]);
                }
                int[] incomeRange = targetq.getIncomeRange();
                if (incomeRange != null && incomeRange.length > 1) {
                    target.setMinIncome(incomeRange[0]);
                    target.setMaxIncome(incomeRange[1]);
                }
            }
        }

        return data.list(daoQuery).stream().map(s -> {
            final SurveyTargetDTO target = new SurveyTargetDTO();
            if (s.getTarget() != null) {
                target.setCountry(s.getTarget().getCountry());
                target.setAgeRange(new int[]{s.getTarget().getMinAge(), s.
                    getTarget().getMaxAge()});
                target.setIncomeRange(new int[]{s.getTarget().getMinIncome(), s.
                    getTarget().getMaxIncome()});
            }
            return new SurveyDTO() {
                {
                    setId(s.getId());
                    setTitle(s.getTitle());
                    setStatus(s.getStatus());
                    setStartDate(s.getStartDate());
                    setTarget(target);
                }
            };
        }).collect(Collectors.toList());
    }

    @Override
    public void insert(SurveyDTO survey) {
        SurveyDO s = data.createSurvey();
        s.setStartDate(survey.getStartDate());
        s.setStatus(SurveyStatus.NEW);
        s.setTitle(survey.getTitle());

        SurveyTargetDTO target = survey.getTarget();
        if (target != null) {
            SurveyTargetDO t = data.createSurveyTarget();
            t.setCountry(target.getCountry());
            t.setGender(t.getGender());
            
            int[] ageRange = target.getAgeRange();
            if (ageRange != null && ageRange.length > 1) {
                t.setMaxAge(target.getAgeRange()[1]);
                t.setMinAge(target.getAgeRange()[0]);
            }

            int[] incomeRange = target.getIncomeRange();
            if (incomeRange != null && incomeRange.length > 1) {
                t.setMaxIncome(target.getIncomeRange()[1]);
                t.setMinIncome(target.getIncomeRange()[0]);
            }

            s.setTarget(t);
        }
        data.add(s);
    }

    @Override
    public void update(SurveyDTO survey) {
        SurveyDO s = data.createSurvey();
        s.setId(survey.getId());
        s.setStartDate(survey.getStartDate());
        s.setStatus(survey.getStatus());
        s.setTitle(survey.getTitle());

        SurveyTargetDTO target = survey.getTarget();
        if (target != null) {
            SurveyTargetDO t = data.createSurveyTarget();
            t.setCountry(target.getCountry());
            t.setGender(t.getGender());
            t.setMaxAge(target.getAgeRange()[1]);
            t.setMinAge(target.getAgeRange()[0]);
            t.setMaxIncome(target.getIncomeRange()[1]);
            t.setMinIncome(target.getIncomeRange()[0]);

            s.setTarget(t);
        }
        data.update(s);
    }

    @Override
    public void delete(String id) {
        data.delete(id);
    }
}
