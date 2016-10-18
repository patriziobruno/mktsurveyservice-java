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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.dstc.mkts.data.SurveyDAO;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyStatus;
import net.dstc.mkts.data.SurveyTargetDO;

/**
 *
 * @author eul0860
 */
public class DataConverters {

    private static class Helper {

        private final static Map<Class, Converter> INSTANCES = new ConcurrentHashMap<>();
    }

    public static Converter getConverter(SurveyDAO dao) {
        Converter converter;
        Class cl = dao.getClass();
        if (!Helper.INSTANCES.containsKey(cl)) {
            converter = new Converter(dao);
            Helper.INSTANCES.put(cl, converter);
        } else {
            converter = Helper.INSTANCES.get(cl);
        }
        return converter;
    }

    public static class Converter {

        private final SurveyDAO data;

        private Converter(SurveyDAO data) {
            this.data = data;
        }

        public SurveyDTO surveyDOtoDTO(SurveyDO dataObject) {
            SurveyDTO rv = null;
            if (dataObject != null) {
                final SurveyTargetDTO target = surveyTargetDOtoDTO(dataObject.getTarget());
                rv = new SurveyDTO();

                rv.setId(dataObject.getId());
                rv.setTitle(dataObject.getTitle());
                rv.setStatus(dataObject.getStatus());
                rv.setStartDate(dataObject.getStartDate());
                rv.setTarget(target);
            }
            return rv;
        }

        public SurveyTargetDTO surveyTargetDOtoDTO(SurveyTargetDO targetSrc) {
            SurveyTargetDTO target = null;
            if (targetSrc != null) {
                target = new SurveyTargetDTO() {
                    {
                        setCountry(targetSrc.getCountry());
                        setAgeRange(new int[]{targetSrc.getMinAge(), targetSrc.getMaxAge()});
                        setIncomeRange(new int[]{targetSrc.getMinIncome(), targetSrc.getMaxIncome()});
                    }
                };
            }
            return target;
        }

        public SurveyDO surveyDTOtoDO(SurveyDTO dto) {
            SurveyDO survey = data.createSurvey();
            survey.setId(dto.getId());
            survey.setStartDate(dto.getStartDate());
            survey.setStatus(SurveyStatus.NEW);
            survey.setTitle(dto.getTitle());
            survey.setTarget(surveyTargetDTOtoDO(dto.getTarget()));

            return survey;
        }

        public SurveyTargetDO surveyTargetDTOtoDO(SurveyTargetDTO dto) {
            SurveyTargetDO target = null;

            if (dto != null) {
                target = data.createSurveyTarget();
                target.setCountry(dto.getCountry());
                target.setGender(target.getGender());

                int[] ageRange = dto.getAgeRange();
                if (ageRange != null && ageRange.length > 1) {
                    target.setMaxAge(dto.getAgeRange()[1]);
                    target.setMinAge(dto.getAgeRange()[0]);
                }

                int[] incomeRange = dto.getIncomeRange();
                if (incomeRange != null && incomeRange.length > 1) {
                    target.setMaxIncome(dto.getIncomeRange()[1]);
                    target.setMinIncome(dto.getIncomeRange()[0]);
                }
            }
            return target;
        }
    }
}
