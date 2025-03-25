package com.senne.oneiros.tools.argumentTypes;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.senne.oneiros.item.ItemRegister;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ItemKeyArgumentType implements CustomArgumentType<NamespacedKey, Key>, CustomArgumentType.Converted<NamespacedKey, Key> {
    /*@Override
    public NamespacedKey parse(StringReader stringReader) {
        String arg = stringReader.getString();
        if (!ItemRegister.getItems().stream().anyMatch(item -> item.getNamespacedKey().asString().equals(arg))) return null;
        return NamespacedKey.fromString(stringReader.getString());
    }*/

    @Override
    public NamespacedKey convert(Key nativeType) {
        return new NamespacedKey(nativeType.namespace().toLowerCase(), nativeType.value().toLowerCase());
    }

    @Override
    public ArgumentType<Key> getNativeType() {
        return ArgumentTypes.key();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String arg = builder.getRemainingLowerCase();

        ItemRegister.getItems().forEach(item -> {
            if (item.getNamespacedKey().asString().toLowerCase().startsWith(arg) || item.getKey().startsWith(arg)) builder.suggest(item.getNamespacedKey().asString());
        });
        return builder.buildFuture();
    }

    @Override
    public Collection<String> getExamples() {
        return ImmutableList.of(ItemRegister.getItems().getFirst().getNamespacedKey().asString());
    }
}
