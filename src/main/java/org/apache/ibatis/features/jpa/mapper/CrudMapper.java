/**
 *    Copyright 2009-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.features.jpa.mapper;

import org.apache.ibatis.features.jpa.domain.Example;
import org.apache.ibatis.features.jpa.generator.impl.*;
import org.apache.ibatis.features.jpa.annotation.CustomProvider;

import java.util.List;

public interface CrudMapper<T, ID> extends Mapper<T, ID> {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    @CustomProvider(SaveSqlGeneratorImpl.class)
    <S extends T> int save(S entity);

    @CustomProvider(SaveAutoIncrementKeyGeneratorImpl.class)
    <S extends T> int saveAutoIncrementKey(S entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @CustomProvider(SaveAllGeneratorImpl.class)
    <S extends T> int saveAll(List<S> entities);

    @CustomProvider(SaveAllAutoIncrementKeyGeneratorImpl.class)
    <S extends T> int saveAllAutoIncrementKey(List<S> entities);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @CustomProvider(FindByIdGeneratorImpl.class)
    T findById(ID id);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @CustomProvider(CountAllGeneratorImpl.class)
    long count();

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @CustomProvider(DeleteByIdGeneratorImpl.class)
    int deleteById(ID id);

    /**
     * Deletes the given entities.
     *
     * @param ids
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @CustomProvider(DeleteAllGeneratorImpl.class)
    int deleteAll(List<? extends ID> ids);

    /**
     * Deletes all entities managed by the repository.
     */
    @CustomProvider(ClearGeneratorImpl.class)
    int clear();

    @CustomProvider(UpdateByPrimaryKeySelectiveGeneratorImpl.class)
    <S extends T> int updateByPrimaryKeySelective(S entity);

    @CustomProvider(UpdateByPrimaryKeyGeneratorIml.class)
    <S extends T> int updateByPrimaryKey(S entity);

    /**
     * @param entity
     * @param <S>
     * @return
     */
    @CustomProvider(SaveSelectiveGeneratorImpl.class)
    <S extends T> int saveSelective(S entity);

    @CustomProvider(SaveSelectiveAutoIncrementGeneratorImpl.class)
    <S extends T> int saveSelectiveAutoIncrement(S entity);

    @CustomProvider(SelectByEntityImpl.class)
    T findOne(T example);

    @CustomProvider(SelectByEntityImpl.class)
    List<T> findList(T example);

    @CustomProvider(SelectByExampleImpl.class)
    List<T> findByExample(Example<T> example);
}