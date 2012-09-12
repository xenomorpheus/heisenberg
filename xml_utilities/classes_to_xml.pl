#!/usr/bin/perl

=encoding utf8

=head1 NAME

classes_to_xml.pl - Convert a text dump of classes PDF into xml.

=head1 SYNOPSIS

classes_to_xml.pl [options]

=head1 DESCRIPTION

Convert a text dump of classes PDF into xml.

=head1 OPTIONS

=over 4

=item B<--textfile filename> read in this text grab from the PDR document

=item B<--debug>    debug level

=item B<--help>     display brief help message

=item B<--version>  display program version

=back

=head1 DIAGNOSTICS

<description of error messages>
=head1 EXAMPLES

classes_to_xml.pl [options]

=head1 FILES

The command line options

=head1 COPYRIGHT

Copyright (c) 2012.
All Rights Reserved

=cut

######################################################################
## Pragmas

use strict;
use utf8;
use warnings;

# Program version
our $VERSION = '1.00';

######################################################################
## Required Libraries

# CPAN
use autodie;
use FileHandle;
use Carp qw(carp confess);
use Params::Check qw(check last_error);
use Readonly;
use Getopt::Long qw(:config auto_help auto_version);
use Pod::Usage;
use Data::Dumper;

#use FindBin;

# Custom

######################################################################
## Constants

Readonly my $PARAMS_CHECK_VERBOSE => 0;

#Readonly my $LOG4PERL_CONFIG      => "$FindBin::Bin/../etc/Log4Perl.conf";

######################################################################

sub main {
    my $debug_mode = 0;
    my $textfile;
    GetOptions( 'debug=i' => \$debug_mode, 'textfile=s' => \$textfile ) or pod2usage(1);

    if ( !-f $textfile ) {
        pod2usage( -verbose => 1, -message => "missing/invalid textfile\n", );
    }

    my $class_hash = classes_text_to_hash( 'textfile' => $textfile, 'debug_mode' => $debug_mode );

    #    print Data::Dumper::Dumper( $class_hash);
    print class_hash_to_string( class_hash => $class_hash );
    return;
} ## end sub main

main();
exit 0;

sub class_hash_to_string {
    my %arg = @_;
    my ($class_hash);
    my %tmpl = ( class_hash => { store => \$class_hash }, );
    check( \%tmpl, \%arg, 0 ) or confess last_error();

    my $output = '';
    foreach my $character_class ( keys %$class_hash ) {
        $output .= "<class id=\"$character_class\"";
        foreach my $property ( keys %{ $class_hash->{$character_class} } ) {
            my $property_xml_key = $property;
            $property_xml_key =~ s/\W//g;
            $property_xml_key = lcfirst $property_xml_key;
            $output .= " $property_xml_key=\"" . $class_hash->{$character_class}{$property} . "\"";
        } ## end foreach my $property ( keys %{ $class_hash->{$character_class...}})
        $output .= "></class>\n";
    } ## end foreach my $character_class ( keys %$class_hash)
    return $output;
} ## end sub class_hash_to_string

sub classes_text_to_hash {
    my %arg = @_;
    my ( $textfile, $debug_mode );
    my %tmpl
        = ( textfile => { default => 0, store => \$textfile }, debug_mode => { default => 0, store => \$debug_mode }, );
    check( \%tmpl, \%arg, 0 ) or confess last_error();

    my $fh = FileHandle->new( $textfile, 'r' );
    my $mode = 'header';
    my %class_details;
    my @class_names_ordered;
    my @property_values;
    my $line = 0;
LINE: while ( defined( my $entry = $fh->getline ) ) {
        chomp $entry;
        $line++;
        print "debug: Line $line, entry: '$entry'\n" if $debug_mode;
        if ( $mode eq 'header' ) {
            if ( ( $entry eq 'Combat Classes Magic Classes' ) || ( $entry eq 'Stealth Classes Hybrid Classes' ) ) {
                $mode = 'class-name-list';
                print "debug: Change mode: $mode\n" if $debug_mode;
                next LINE;
            }
            else {
                confess "Error: Line $line, Mode: $mode, Unexpected entry: $entry";
            }
        } ## end if ( $mode eq 'header' )
        if ( $mode eq 'class-name-list' ) {
            if ( $entry =~ /\A\s*\d+\s*\z/x ) {

                # fall through
                $mode = 'property-list';
                print "debug: Change mode: $mode\n" if $debug_mode;
            } ## end if ( $entry =~ /\A\s*\d+\s*\z/x )
            elsif ( $entry =~ /\A\s*([\w\s]*\w)\s*\z/x ) {
                my $class_name = $1;
                push @class_names_ordered, $class_name;
                if ( exists $class_details{$class_name} ) {
                    confess "Error: Line $line, Mode: $mode, Duplicated class name: $entry";
                }
                $class_details{$class_name} = {};
                next LINE;
            } ## end elsif ( $entry =~ /\A\s*([\w\s]*\w)\s*\z/x )
            else {
                confess "Error: Line $line, Mode: $mode, Unexpected entry: $entry";
            }
        } ## end if ( $mode eq 'class-name-list' )

        if ( $mode eq 'property-list' ) {
            if ( ( scalar @property_values ) == ( scalar @class_names_ordered ) ) {
                my $property_name = $entry;
                my $offset        = 0;
                foreach my $class_name (@class_names_ordered) {
                    $class_details{$class_name}{$property_name} = $property_values[$offset];
                    $offset++;
                }
                @property_values = ();
            } ## end if ( ( scalar @property_values ) == ( scalar...))
            else {
                push @property_values, $entry;
            }
            next LINE;
        } ## end if ( $mode eq 'property-list' )

        confess "Error: Line $line, Bad Mode: $mode, entry: $entry\n";

    } ## end LINE: while ( defined( my $entry = $fh->getline ) )
    return \%class_details;
} ## end sub classes_text_to_hash
