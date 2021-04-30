/*
 *  Copyright 2021 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import { Token } from '@lumino/coreutils';

const name = '@beakerx/extension-gate';

/**
 * An interface that describes extension gate implementations.
 */
export interface IExtensionGate {
  /**
   * Resolves to `true` if the gate determines the plugin should activate.
   *
   * @param plugin The ID of the plugin being validated.
   *
   * #### Notes
   * This function should never reject.
   * Errors should resolve to a reasonable truth value depending on context.
   */
  check(plugin: string): Promise<boolean>;
}

/**
 * Token representing a concrete extension gate.
 */
export const IExtensionGate = new Token<IExtensionGate>(name);
