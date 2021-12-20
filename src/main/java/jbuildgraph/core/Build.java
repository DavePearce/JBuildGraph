// Copyright 2021 David James Pearce
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package jbuildgraph.core;

import jbuildstore.core.Content;
import jbuildgraph.util.Trie;

public interface Build {

	/**
	 * Represents a factory for constructing build tasks (e.g. for compiling Whiley
	 * files to WyIL files, etc).
	 *
	 * @author David J. Pearce
	 *
	 */
	public interface Platform<T> {
		/**
		 * Initialise a fresh task for building artifacts in a given context (i.e. where
		 * we know what the configuration is, what the source files are, etc).
		 *
		 * @param context Necessary context for initialising the task.
		 *
		 * @return
		 */
		public Task initialise(T context);
	}

	/**
	 * A shortlived unit of work responsible for generating a given build artifact
	 * (e.g. converting one or more files of a given type into a given target file).
	 * Tasks which are not dependent on each other may be scheduled in parallel.
	 *
	 * @author David J. Pearce
	 *
	 */
	public interface Task {
		/**
		 * Apply this task to a given repository.
		 *
		 * @param repository
		 * @return
		 */
		public boolean apply(Content.Store<Trie> repository);
	}
}
